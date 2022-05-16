package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Order;
import com.svalero.cesped.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDao {
    private Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }
    //TODO CLASE ORDERDAO
   // public void addOrder (Order order) {
     //   String sql = "INSERT INTO PEDIDOS (NOMBRE, PRECIO, STOCK, ID_PROVEEDOR) VALUES (?, ?, ?, ?)";

       // try {
       //     PreparedStatement statement = connection.prepareStatement(sql);
       //     statement.setString(1, product.getName());
       //     statement.setFloat(2, product.getPrice());
      //      statement.setInt(3, product.getStock());
       //     statement.setString(4, String.valueOf(product.getSupplier()));
       //     statement.executeUpdate();
      //  } catch (SQLException sqle) {
      //      System.out.println("Error BBDD al añadir producto");
      //      sqle.printStackTrace();
      //  }
    }
