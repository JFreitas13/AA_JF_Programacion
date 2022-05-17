package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDao {

    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public void addProduct (Product product) {
        String sql = "INSERT INTO PRODUCTOS (NOMBRE, PRECIO, STOCK, ID_PROVEEDOR) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setString(4, String.valueOf(product.getSupplier()));
            statement.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Error BBDD al añadir producto");
            sqle.printStackTrace();
        }
    }

    public boolean modifyProduct(String name, Product product) throws SQLException {
        String sql = "UPDATE PRODUCTOS INTO NOMBRE = ?, PRECIO = ?, STOCK = ?, ID_PROVEEDOR = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setFloat(2, product.getPrice());
        statement.setInt(3, product.getStock());
        statement.setString(4, String.valueOf(product.getSupplier()));
        int rows = statement.executeUpdate();

        return rows == 1;
    }

    public boolean deleteProduct(String name) throws SQLException {
        String sql = "DELETE FROM PRODUCTOS WHERE NOMBRE = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        int rows = statement.executeUpdate();

        return rows == 1;
    }

    public void showProduct() {

    }

    public void findOneProduct() {

    }
}
