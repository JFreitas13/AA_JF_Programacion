package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDao {

    private Connection connection;

    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    public void addClient (Client client) {
        String sql = "INSERT INTO CLIENTES (NOMBRE, APELLIDOS, DNI, TELEFONO, EMAIL) VALUES (?, ?, ?, ?, ?)"; //todo CONFIRMAR PARAMETROS webinar 5 1:38 seguir viendo

        //TODO TERMINAR DE AÑADIR PARAMETROS
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getDni());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            statement.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Error BBDD al añadir producto");
            sqle.printStackTrace();
        }
    }
}
