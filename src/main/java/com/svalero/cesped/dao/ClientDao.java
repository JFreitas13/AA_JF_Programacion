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

    //Constructor para conectar a la BBDD
    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    //Metodo para añadir Clientes
    public void addClient(Client client) throws SQLException, ClientAlreadyExistException { //throws hace que la excepción se propague a una capa superior
        //comprobación si existe el cliente.
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

    //Metodo para eliminar Clientes cogiendo su id
    public boolean deleteById(int idClient) throws SQLException {
        String sql = "DELETE FROM CLIENTES WHERE ID_CLIENTE = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idClient);
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    //Metodo para modificar Clientes cogiendo el dni. No usado en la AA
    public boolean modifyClient(String dni, Client client) throws SQLException {
        String sql = "UPDATE CLIENTES SET NOMBRE = ?, APELLIDOS = ?, DNI = ?, TELEFONO = ?, EMAIL = ? WHERE DNI = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, client.getName());
        statement.setString(2, client.getSurname());
        statement.setString(3, client.getDni());
        statement.setString(4, client.getPhone());
        statement.setString(5, client.getEmail());
        statement.setString(6, dni);
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    //Metodo para modificar Clientes cogiendo el id.
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

    //Metodo para buscar todos los clientes y listarlos.
    public ArrayList<Client> findAllClient() throws SQLException {
        String sql = "SELECT * FROM CLIENTES ORDER BY NOMBRE";
        ArrayList<Client> clients = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(); //cursor. objeto que apunta a to do el contenido de la tabla
        while (resultSet.next()) { //mientras haya datos
            Client client = fromResultSet(resultSet);
            clients.add(client);
        }

        return clients;
    }

    //Metodo para buscar los clientes y listarlos en función del texto que indique el usuario. Se buscará el todas las columnas..
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
    //Optional para objetos que pueden ser nulos
    //Método para buscar por DNI
    public Optional<Client> findByDni(String dni) throws SQLException {
        String sql = "SELECT * FROM CLIENTES WHERE DNI = ?";
        Client client = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,dni); //parametro pasado
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            client = new Client();
            client.setIdClient(Integer.parseInt(resultSet.getString("ID_CLIENTE"))); //importante que el nombre sea el de las columnas de BBDD
            client.setName(resultSet.getString("NOMBRE"));
            client.setSurname(resultSet.getString("APELLIDOS"));
            client.setDni(resultSet.getString("DNI"));
            client.setPhone(resultSet.getString("TELEFONO"));
            client.setEmail(resultSet.getString("EMAIL"));
        }

        return  Optional.ofNullable(client);
    }

    //Método para buscar por id
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

    //Metodo para determinar si el cliente ya existe en base a su DNI.
    private boolean existClient(String dni) throws SQLException { //private porque es para uso interno
        Optional<Client> client = findByDni(dni);
        return client.isPresent();
    }

    //TODO QUITAMOS DE LA AA?
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

    //Metodo para usar en los listados que devuelven ResultSet
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
