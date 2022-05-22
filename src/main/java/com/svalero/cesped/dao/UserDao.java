package com.svalero.cesped.dao;

import com.svalero.cesped.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<User> login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM USUARIOS WHERE USERNAME = ? AND PASS = ?";
        User user = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id_usuario"));
            user.setName(resultSet.getString("nombre"));
            user.setUsername(resultSet.getString("username")); //quito la pass que no hace falta gardarla siempre
            user.setRole(resultSet.getString("role"));
        }

        return Optional.ofNullable(user);
    }
}
