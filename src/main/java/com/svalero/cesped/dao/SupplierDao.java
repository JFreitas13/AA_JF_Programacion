package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDao {

    private Connection connection;

    public SupplierDao(Connection connection) {
        this.connection = connection;
    }

    //añadir proveedor
    public void addSupplier (Supplier supplier) {
        String sql = "INSERT INTO PROVEEDORES (NOMBRE, CIF, TELEFONO, EMAIL) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getCif());
            statement.setString(3, supplier.getPhone());
            statement.setString(4, supplier.getEmail());
            statement.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Error BBDD al añadir proveedor");
            sqle.printStackTrace();
        }
    }
    //lista de todos los proveerdores
    public ArrayList<Supplier> findAll() throws SQLException{
        String sql = "SELECT * FROM PROVEEDORES";
        ArrayList<Supplier> suppliers = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Supplier supplier = new Supplier( "a", "12345678Y", "61541408", "j@jk.com");
            supplier.setName(result.getString("NOMBRE"));
            supplier.setCif(result.getString("CIF"));
            supplier.setPhone(result.getString("TELEFONO"));
            supplier.setEmail(result.getString("EMAIL"));
            suppliers.add(supplier);
        }
        statement.close();

        return suppliers;
    }

    public Supplier findByCif(int id) throws SQLException {
        String sql = "SELECT * FROM PROVEEDORES WHERE ID_PROVEEDOR = ?";
        Supplier supplier = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, String.valueOf(id));
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            supplier = new Supplier( "a", "12345678Y", "61541408", "j@jk.com");
            supplier.setName(resultSet.getString("NOMBRE"));
            supplier.setCif(resultSet.getString("CIF"));
            supplier.setPhone(resultSet.getString("TELEFONO"));
            supplier.setEmail(resultSet.getString("EMAIL"));
        }

        return supplier;
    }

    //modificar proveedor (que cif quiero modificar y que nuevos datos quiero)
    public  boolean modify(String cif, Supplier supplier) throws SQLException {
        String sql = "UPDATE PROVEEDORES SET NOMBRE = ?, CIF = ?, TELEFONO = ?, EMAIL = ? WHERE CIF = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, supplier.getName());
        statement.setString(2, supplier.getCif());
        statement.setString(3, supplier.getPhone());
        statement.setString(4, supplier.getEmail());
        statement.setString(5, cif);
        //nº filas modificadas
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    public boolean delete(String cif, Supplier supplier) throws SQLException {
        String sql = "DELETE FROM PROVEEDORES WHERE CIF = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cif);
        int rows = statement.executeUpdate();
        return rows == 1;
    }

}
