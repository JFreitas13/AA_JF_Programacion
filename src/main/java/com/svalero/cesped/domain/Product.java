package com.svalero.cesped.domain;

public class Product {

    //private int id;
    private String name;
    private float price;
    private float stock;
    private Supplier supplier;

    public Product() {
        //this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.supplier = supplier;
    }

    //public int getId() {
    //    return id;
   // }

    //public void setId(int id) {
    //    this.id = id;
   // }

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
        return (int) stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
