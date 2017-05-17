package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoSqlite extends BaseDao implements SupplierDao {

    @Override
    public Supplier find(int id) {
        Supplier supplier = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement("select * from suppliers where id=(?)");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                supplier = new Supplier(
                        rs.getString("name"),
                        rs.getString("description")
                );
                supplier.setId(rs.getInt("id"));
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return supplier;
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from suppliers");
            while(rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("name"),
                        rs.getString("description")
                );
                supplier.setId(rs.getInt("id"));
                suppliers.add(supplier);
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println("Conne unnecessary");
            System.out.println(e.getMessage());
        }
        return suppliers;
    }
}
