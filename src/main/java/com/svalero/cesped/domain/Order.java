package com.svalero.cesped.domain;

import java.time.LocalDate;

public class Order {

    private int idCliente;
    private int idProducto;
    private LocalDate date;
    private float cantidad;

    //TODO REVISAR CLASE ORDER
    public Order(LocalDate date, float cantidad) {
        this.date = date;
        this.cantidad = cantidad;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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
}
