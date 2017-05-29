package com.codecool.shop;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.model.Basket;
import com.codecool.shop.shutdownHook.ShutdownHook;
import spark.Request;
import spark.Response;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static spark.Spark.*;

public class Application {
    private Connection connection;
    private static Application app;
    private ProductController productController;
    private BasketController basketController;

    private Application() {
        try {
            this.connectToDb();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Application initialization failed...");
        }
        app = this;
        this.productController = new ProductController();
        this.basketController = new BasketController();
    }

    public static void run(String[] args) {
        System.out.println("Initializing application...");
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());

        if (app == null) {
            try {
                new Application();
                if (args.length > 0) {
                    if (Objects.equals(args[0], "--init-db")) {
                        app.dropTables();
                        app.createTables();
                        app.fillTables();
                    } else if (Objects.equals(args[0], "--migrate-db")) {
                        app.createTables();
                    }
                }
                Integer requiredTablesCount = 4;
                if (app.tablesCounter() == requiredTablesCount) {
                    exception(Exception.class, (e, req, res) -> e.printStackTrace());
                    staticFileLocation("/public");
                    port(8888);
                    app.routes();
                } else {
                    System.out.println("Database missing tables...");
                }
            } catch (SQLException e) {
                System.out.println("Application initialization failed...");
                e.printStackTrace();
            }
        } else {
            System.out.println("Application already running!");
        }
    }

    private void connectToDb() throws SQLException {
        System.out.println("Connection to DB...");
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }

    private void dropTables() throws SQLException {
        Statement statement = connection.createStatement();
        List<String> tables = new ArrayList<>();

        ResultSet rs = statement.executeQuery("" +
                "SELECT name FROM sqlite_master WHERE type='table' AND name!='sqlite_sequence'");
        while (rs.next()) {
            tables.add(rs.getString("name"));
        }
        for (String table : tables) {
            statement.execute("DROP TABLE '" + table + "'");
        }
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(prepareQuery("products.sql"));
        statement.execute(prepareQuery("categories.sql"));
        statement.execute(prepareQuery("suppliers.sql"));
    }

    private void fillTables() throws SQLException {
        Statement statement = connection.createStatement();
        String[] files = {
                prepareQuery("productsData.sql"),
                prepareQuery("categoriesData.sql"),
                prepareQuery("suppliersData.sql")};
        for (String file : files) {
            for (String line : file.split(";")) {
                statement.execute(line);
            }
        }
    }

    private Integer tablesCounter() throws SQLException {
        Integer tablesCounter = 0;
        List<String> requiredTables = new ArrayList<>();
        requiredTables.add("sqlite_sequence");
        requiredTables.add("products");
        requiredTables.add("categories");
        requiredTables.add("suppliers");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
        while (rs.next()) {
            if (requiredTables.contains(rs.getString("name"))) {
                tablesCounter++;
            }
        }
        return tablesCounter;
    }

    private String prepareQuery(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("src/main/resources/sql/" + fileName)
            );
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public Connection getConnection() {
        return connection;
    }

    public static Application getApp() {
        return app;
    }

    private void routes() {

        before((request, response) -> {
            if (request.session().isNew()) {
                request.session().attribute("basket", new Basket());
            }
        });
        get("/", (Request req, Response res) -> {
            res.redirect("/products");
            return null;
        });
        get("/products", productController::index);
        get("/products/new", productController::add);
        post("/products/new", productController::add);
        post("/products/:id/add_to_cart", basketController::addToCartAction);
        get("/basket", basketController::show);
    }
}