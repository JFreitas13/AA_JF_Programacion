package com.svalero.cesped.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Clase no usada en la AA
public class Order {

    private int idOrder;
    private int idClient;
    private int idProduct;
    private LocalDate date;
    private float cantidad;

    private Client client; //objeto relacionado con el pedido
    private List<Product> products;

    public Order() {
        products = new ArrayList<>();
    }

    public Order(LocalDate date, float cantidad, Client client) {
        this.date = date;
        this.cantidad = cantidad;
        this.client = client;
        products = new ArrayList<>();
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

   public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
