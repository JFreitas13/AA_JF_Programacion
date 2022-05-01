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
        String sql = "INSERT INTO PRODUCTOS (NOMBRE, PRECIO, STOCK, ID_PROVEEDOR) VALUES (?, ?, ?, ?)"; //todo CONFIRMAR PARAMETROS webinar 5 1:38 seguir viendo

        //TODO TERMINAR DE AÑADIR PARAMETROS
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

    public void modifyProduct() {

    }

    public void deleteProduct() {

    }

    public void findAllProduct() {

    }

    public void findOneProduct() {

    }
}
