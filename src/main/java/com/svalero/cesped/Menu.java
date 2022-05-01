package com.svalero.cesped;

import com.svalero.cesped.dao.Database;
import com.svalero.cesped.dao.ProductDao;
import com.svalero.cesped.domain.Product;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    private Scanner keyboard;
    private Database database;
    private Connection connection; //TODO


    public Menu() {
        keyboard = new Scanner(System.in);
    }

    public void connect() {
        database = new Database();
        connection = database.getConnection();
    }

    public void showMenu() {
        connect();
        String choice = null;

        do {
            System.out.println("Programa de Gestión");
            System.out.println("1. Hacer pedido");
            System.out.println("2. Consultar pedido");
            System.out.println("3. Añadir proveedor");
            System.out.println("4. Consultar proveedor");
            System.out.println("5. Añadir producto");
            System.out.println("6. Modificar producto");
            System.out.println("7. Consultar producto");
            System.out.println("8. Añadir cliente");
            System.out.println("9. Modificar cliente");
            System.out.println("10. Consultar cliente");
            System.out.println("11. Salir");
            choice = keyboard.nextLine();

            switch (choice) {
                case "1":
                    addOrder();
                    break;
                case "2":
                    showOrder();
                    break;
                case "3":
                    addSupplier();
                    break;
                case "4":
                    showSupplier();
                    break;
                case "5":
                    addProduct();
                    break;
                case "6":
                    modifyProduct();
                    break;
                case "7":
                    showProduct();
                    break;
                case "8":
                    addClient();
                    break;
                case "9":
                    modifyClient();
                    break;
                case "10":
                    showCliente();
                    break;
            }
        } while (!choice.equals("6"));

    }

    private void addOrder() {
        System.out.println("Nombre y apellidos: ");
        String name = keyboard.nextLine();
        System.out.println("DNI: ");
        String dni = keyboard.nextLine();
        System.out.println("Metros: ");
        int quantity = Integer.parseInt(keyboard.nextLine());

    }

    private void showOrder(){

        }

    private void addSupplier() {
        System.out.println("Nombre Comercial: ");
        String name = keyboard.nextLine();
        System.out.println("CIF: ");
        String cif = keyboard.nextLine();
        System.out.println("País: ");
        String country = keyboard.nextLine();
        System.out.println("Unidades mínimas por pedido: ");
        int minCuantity = Integer.parseInt(keyboard.nextLine());
        System.out.println("Valor mínimo por pedido (en €): ");
        float minOrder = Integer.parseInt(keyboard.nextLine());
    }

    private void showSupplier() {

    }

    private void addProduct() {
        System.out.println("Nombre: ");
        String name = keyboard.nextLine();
        System.out.println("Precio: ");
        String price = keyboard.nextLine();
        System.out.println("Stock: ");
        String stock = keyboard.nextLine();
        System.out.println("Proveedor: ");
        String supplier = keyboard.nextLine();
        //Product newProduct = new Product(name.trim(), price.trim(), stock.trim(), supplier.trim());

        //ProductDao productDao = new ProductDao(connection);
        //productDao.addProduct(newProduct);
        //System.out.println("El producto se ha añadido correctamente");
    }

    private void modifyProduct() {

    }

    private void showProduct() {

    }

    private void addClient() {

    }

    private void modifyClient() {

    }

    private void showCliente() {

    }


}
