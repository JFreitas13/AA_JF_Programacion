package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Product;
import com.svalero.cesped.domain.Supplier;

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

    public void add(Product product) throws SQLException {
        String sql = "INSERT INTO PRODUCTOS (NOMBRE, PRECIO, STOCK, ID_PROVEEDOR) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setString(4, product.getIdSupplier());
            statement.executeUpdate();

        System.out.println("Producto sql: " + product.getIdSupplier());
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
    public ArrayList<Product> findAll(String searchText) throws SQLException {
        String sql = "SELECT * FROM PRODUCTOS INSTR(NOMBRE, ?) != 0 OR INSTR(PRECIO, ?) != 0 OR INSTR(STOCK, ?) != 0 OR INSTR(ID_PROVEEDOR, ?) != 0 ORDER BY nombre";
        ArrayList<Product> products = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Product product = fromResultSet(resultSet);
            products.add(product);
        }
        statement.close();

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

    private Product fromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setIdProduct(resultSet.getInt("id_producto"));
        product.setName(resultSet.getString("nombre"));
        product.setPrice(Float.parseFloat(resultSet.getString("precio")));
        product.setStock(Integer.parseInt(resultSet.getString("stock")));
        product.setIdSupplier(resultSet.getString("id_proveedor"));
        return product;
    }
}
