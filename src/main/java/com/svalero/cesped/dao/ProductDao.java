package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ProductDao {

    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public void add(String supplierId, Product product) throws SQLException {
        String sql = "INSERT INTO PRODUCTOS (NOMBRE, PRECIO, STOCK, ID_PROVEEDOR) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setString(4, String.valueOf(product.getIdSupplier()));
            statement.executeUpdate();
    }


    public boolean modify(Product product) throws SQLException {
        String sql = "UPDATE PRODUCTOS INTO NOMBRE = ?, PRECIO = ?, STOCK = ?, ID_PROVEEDOR = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setFloat(2, product.getPrice());
        statement.setInt(3, product.getStock());
        statement.setString(4, String.valueOf(product.getSupplier()));
        int rows = statement.executeUpdate();

        return rows == 1;
    }

    public boolean delete(String name) throws SQLException {
        String sql = "DELETE FROM PRODUCTOS WHERE NOMBRE = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        int rows = statement.executeUpdate();

        return rows == 1;
    }

    //listar todos los productos
    public ArrayList<Product> findAll() throws SQLException {
        String sql = "SELECT * FROM PRODUCTOS";
        ArrayList<Product> products = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();
            product.setIdProduct(Integer.parseInt(resultSet.getString("ID_PRODUCTO")));
            product.setName(resultSet.getString("NOMBRE"));
            product.setPrice(Float.parseFloat(resultSet.getString("PRECIO")));
            product.setStock(Integer.parseInt(resultSet.getString("STOCK")));
            product.setIdSupplier(resultSet.getInt("ID_PROVEEDOR"));
            products.add(product);
        }
        return products;
    }

    public Optional<Product> findByName(String name) throws SQLException {
        String sql = "SLECT * FROM PRODUCTOS WHERE NOMBRE = ?";
        Product product = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            product = new Product();
            product.setIdProduct(resultSet.getInt("ID_PRODUCTO"));
            product.setName(resultSet.getString("NOMBRE"));
            product.setPrice(resultSet.getFloat("PRECIO"));
            product.setStock(resultSet.getInt("STOCK"));
            //product.setIdSupplier(resultSet.getInt("ID_PROVEEDOR"));
        }
        return Optional.ofNullable(product);
    }
}
