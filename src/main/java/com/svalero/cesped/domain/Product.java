package com.svalero.cesped.domain;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int idProduct;
    private String name;
    private float price;
    private int stock;
    private int idSupplier;

    private Supplier supplier; //para relacionar con Supplier

    private List<Order> orders; //relacionar productos con pedidos

    public Product() {
        orders = new ArrayList<>(); //si creo un producto nuevo debo inicializar el array
    }

    public Product(String name, float price, int stock, Supplier supplier) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.supplier = supplier;
        orders = new ArrayList<>();
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
