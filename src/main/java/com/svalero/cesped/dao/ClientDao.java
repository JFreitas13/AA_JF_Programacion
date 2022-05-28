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

    public boolean deleteClient(int idClient) throws SQLException {
        String sql = "DELETE FROM CLIENTES WHERE ID_CLIENTE = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idClient);
        int rows = statement.executeUpdate();

        return rows == 1;
    }

   public boolean deleteById(int idClient) throws SQLException {
        String sql = "DELETE FROM CLIENTES WHERE ID_CLIENTE = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, String.valueOf(idClient));
        int rows = statement.executeUpdate();

        return rows == 1;
    }


    public boolean modifyClient(String dni, Client client) throws SQLException {
        String sql = "UPDATE CLIENTES SET NOMBRE = ?, APELLIDOS = ?, DNI = ?, TELEFONO = ?, EMAIL = ? WHERE DNI = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, client.getName());
        statement.setString(2, client.getSurname());
        statement.setString(3, client.getDni());
        statement.setString(4, client.getPhone());
        statement.setString(5, client.getEmail());
        statement.setString(4, dni);
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    public boolean modifyById(int clientId, Client client) throws SQLException {
        String sql = "UPDATE CLIENTES SET NOMBRE = ?, APELLIDOS = ?, DNI = ?, TELEFONO = ?, EMAIL = ? WHERE ID_CLIENTE = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, client.getName());
        statement.setString(2, client.getSurname());
        statement.setString(3, client.getDni());
        statement.setString(4, client.getPhone());
        statement.setString(5, client.getEmail());
        statement.setInt(6, clientId);
        int rows = statement.executeUpdate();
        return rows == 1;
    }
    public ArrayList<Client> findAllClient() throws SQLException {
        String sql = "SELECT * FROM CLIENTES";
        ArrayList<Client> clients = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Client client = fromResultSet(resultSet);
            clients.add(client);
        }
        return clients;
    }
    public ArrayList<Client> findAllClient(String searchText) throws SQLException {
        String sql = "SELECT * FROM CLIENTES WHERE INSTR(NOMBRE, ?) != 0 OR INSTR(APELLIDOS, ?) != 0 OR INSTR(DNI, ?) != 0 OR INSTR(TELEFONO, ?) != 0 OR INSTR(EMAIL, ?) != 0 ORDER BY APELLIDOS";
        ArrayList<Client> clients = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, searchText);
        statement.setString(2, searchText);
        statement.setString(3, searchText);
        statement.setString(4, searchText);
        statement.setString(5, searchText);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Client client = fromResultSet(resultSet);
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

    public Optional<Client> findById(int idClient) throws SQLException {
        String sql = "SELECT * FROM CLIENTES WHERE ID_CLIENTE = ?";
        Client client = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idClient);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            client = fromResultSet(resultSet);
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

    private Client fromResultSet(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setIdClient(resultSet.getInt("id_cliente"));
        client.setName(resultSet.getString("nombre"));
        client.setSurname(resultSet.getString("apellidos"));
        client.setDni(resultSet.getString("dni"));
        client.setPhone(resultSet.getString("telefono"));
        client.setEmail(resultSet.getString("email"));
        return client;
    }
}
