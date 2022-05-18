package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {

    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO PRODUCTOS (NOMBRE, PRECIO, STOCK, ID_PROVEEDOR) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setString(4, String.valueOf(product.getSupplier()));
            statement.executeUpdate();
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

    //listar todos los productos
    public ArrayList<Product> findAllProduct() throws SQLException {
        String sql = "SELECT * FROM PRODUCTOS";
        ArrayList<Product> products = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(Integer.parseInt(resultSet.getString("ID_PRODUCTO")));
            product.setName(resultSet.getString("NOMBRE"));
            product.setPrice(Float.parseFloat(resultSet.getString("PRECIO")));
            product.setStock(Integer.parseInt(resultSet.getString("STOCK")));
            product.setSupplier(resultSet.getString("ID_PROVEEDOR"));
            products.add(product);
        }
        return products;
    }

    public void findOneProduct() {

    }
}
