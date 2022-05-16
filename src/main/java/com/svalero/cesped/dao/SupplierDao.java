package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Product;
import com.svalero.cesped.domain.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierDao {

    private Connection connection;

    public SupplierDao(Connection connection) {
        this.connection = connection;
    }

    public void addSupplier (Supplier supplier) {
        String sql = "INSERT INTO PROVEEDORES (ID_PROVEEDOR, NOMBRE, CIF, TELEFONO, EMAIL) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, supplier.getId());
            statement.setString(2, supplier.getName());
            statement.setString(3, supplier.getCif());
            statement.setString(4, supplier.getPhone());
            statement.setString(5, supplier.getEmail());
            statement.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Error BBDD al añadir proveedor");
            sqle.printStackTrace();
        }

    }
