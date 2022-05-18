package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Client;
import com.svalero.cesped.domain.Supplier;
import com.svalero.cesped.exception.SupplierAlreadyExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SupplierDao {

    private Connection connection;

    public SupplierDao(Connection connection) {
        this.connection = connection;
    }

    //añadir proveedor
    public void addSupplier (Supplier supplier) throws SQLException, SupplierAlreadyExistException {
        if(existSupplier(supplier.getCif()))
            throw new SupplierAlreadyExistException();

        String sql = "INSERT INTO PROVEEDORES (NOMBRE, CIF, TELEFONO, EMAIL) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getCif());
            statement.setString(3, supplier.getPhone());
            statement.setString(4, supplier.getEmail());
            statement.executeUpdate();
        }

    //lista de todos los proveerdores
    public ArrayList<Supplier> findAllSupplier() throws SQLException{
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
    public  boolean modifySupplier(String cif, Supplier supplier) throws SQLException {
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

    public boolean deleteSupplier(String cif) throws SQLException {
        String sql = "DELETE FROM PROVEEDORES WHERE CIF = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cif);
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    public Optional<Supplier> findByCif(String cif) throws SQLException {
        String sql = "SELECT * FROM PROVEEDORES WHERE CIF = ?";
        Supplier supplier = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,cif);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            supplier = new Supplier();
            supplier.setId(Integer.parseInt(resultSet.getString("ID_PROVEEDOR")));
            supplier.setName(resultSet.getString("NOMBRE"));
            supplier.setCif(resultSet.getString("CIF"));
            supplier.setPhone(resultSet.getString("TELEFONO"));
            supplier.setEmail(resultSet.getString("EMAIL"));
        }
        return  Optional.ofNullable(supplier);
    }

    private boolean existSupplier(String cif) throws SQLException { //private porque es para uso interno
        Optional<Supplier> supplier = findByCif(cif);
        return supplier.isPresent();
    }

}
