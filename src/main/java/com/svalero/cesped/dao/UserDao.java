package com.svalero.cesped.dao;

import java.sql.Connection;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }
}
