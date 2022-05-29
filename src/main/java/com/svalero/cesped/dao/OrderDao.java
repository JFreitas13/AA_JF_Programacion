package com.svalero.cesped.dao;

import com.svalero.cesped.domain.Client;
import com.svalero.cesped.domain.Order;
import com.svalero.cesped.domain.Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {

    private Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    //TODO CLASE ORDERDAO. No usada en la AA
    public void add(Client client, List<Product> products, Order order) throws SQLException {
      /*  String sql = "INSERT INTO PEDIDOS (ID_CLIENTE, ID_PRODUCTO, FE_COMPRA, CANTIDAD) VALUES (?, ?, ?, ?)";

        connection.setAutoCommit(false);

        PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, client.getIdClient());
            statement.setInt(2, product.getIdProduct());
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setString(4, String.valueOf(product.getSupplier()));
            statement.setFloat(5, order.getCantidad());
            statement.executeUpdate();
        }

        for (Product product : product) {
            String sql = "INSERT INTO PEDIDOS (ID_CLIENTE, ID_PRODUCTO, FE_COMPRA, CANTIDAD) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, client.getIdClient());
            statement.setInt(2, product.getIdProduct());
            statement.setDate(3, new Date(System.currentTimeMillis())); //Tema fechas en Aula abierta 7
            statement.setString(4, String.valueOf(product.getSupplier()));
            statement.setFloat(5, order.getCantidad());
            statement.executeUpdate();
        }
    }

        public void showOrder () {

        }

        public void modifyOrder () {

        }*/
    }
}

