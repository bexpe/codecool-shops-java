package com.codecool.shop.dao;

import com.codecool.shop.Application;

import java.sql.Connection;

abstract class BaseDao {
    private Connection connection;

    BaseDao() {
        Connection connection = Application.getApp().getConnection();
        this.setConnection(connection);
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }

    Connection getConnection() {
        return connection;
    }
}
