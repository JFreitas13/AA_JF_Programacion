package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Client;
import com.svalero.cesped.exception.ClientAlreadyExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ClientDao {

    private Connection connection;

    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    public void addClient(Client client) throws SQLException, ClientAlreadyExistException { //paso la excepción al menu con throws y que uso tb la de que ya existe el cliente
        if (existClient(client.getDni()))
            throw new ClientAlreadyExistException();

        String sql = "INSERT INTO CLIENTES (NOMBRE, APELLIDOS, DNI, TELEFONO, EMAIL) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, client.getName());
        statement.setString(2, client.getSurname());
        statement.setString(3, client.getDni());
        statement.setString(4, client.getPhone());
        statement.setString(5, client.getEmail());
        statement.executeUpdate();
    }

    public boolean deleteClient(String dni) throws SQLException {
        String sql = "DELETE FROM CLIENTES WHERE DNI = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dni);
        int rows = statement.executeUpdate();

        return rows == 1;
    }


    public boolean modifyClient(String dni, Client client) throws SQLException {
        String sql = "UPDATE CLIENT SET NOMBRE = ?, APELLIDOS = ?, DNI = ?, TELEFONO = ?, EMAIL = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, client.getName());
        statement.setString(2, client.getSurname());
        statement.setString(3, client.getDni());
        statement.setString(4, client.getPhone());
        statement.setString(5, client.getEmail());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    public ArrayList<Client> findAllClient() throws SQLException {
        String sql = "SELECT * FROM CLIENTES";
        ArrayList<Client> clients = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Client client = new Client();
            client.setIdClient(Integer.parseInt(resultSet.getString("ID_CLIENTE")));
            client.setName(resultSet.getString("NOMBRE"));
            client.setSurname(resultSet.getString("APELLIDOS"));
            client.setDni(resultSet.getString("DNI"));
            client.setPhone(resultSet.getString("TELEFONO"));
            client.setEmail(resultSet.getString("EMAIL"));
            clients.add(client);
        }
        return clients;
    }

    //nullPointException - referencia un objecto que esta null. Si un objecto no se instancia sale null
    //Optional para objetos que pueeden ser nulos
    public Optional<Client> findByDni(String dni) throws SQLException {
        String sql = "SELECT * FROM CLIENTES WHERE DNI = ?";
        Client client = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,dni);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            client = new Client();
            client.setIdClient(Integer.parseInt(resultSet.getString("ID_CLIENTE")));
            client.setName(resultSet.getString("NOMBRE"));
            client.setSurname(resultSet.getString("APELLIDOS"));
            client.setDni(resultSet.getString("DNI"));
            client.setPhone(resultSet.getString("TELEFONO"));
            client.setEmail(resultSet.getString("EMAIL"));
        }
        return  Optional.ofNullable(client);
    }

    private boolean existClient(String dni) throws SQLException { //private porque es para uso interno
        Optional<Client> client = findByDni(dni);
        return client.isPresent();
    }

    public Optional<Client> getCliente (String phone)  throws SQLException {
        String sql = "SELECT * FROM CLIENTES WHERE PHONE = ?";
        Client client = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, phone);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            client = new Client();
            client.setIdClient(resultSet.getInt("ID_CLIENTE"));
            client.setName(resultSet.getString("NOMBRE"));
            client.setSurname(resultSet.getString("APELLIDOS"));
            client.setDni(resultSet.getString("DNI"));
            client.setPhone(resultSet.getString("TELEFONO"));
            client.setEmail(resultSet.getString("EMAIL"));
        }

        return Optional.ofNullable(client);
    }
}
