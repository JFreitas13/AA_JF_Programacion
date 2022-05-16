package com.svalero.cesped;

import com.svalero.cesped.dao.ClientDao;
import com.svalero.cesped.dao.Database;
import com.svalero.cesped.dao.ProductDao;
import com.svalero.cesped.domain.Client;
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
            System.out.println("4. Modificar proveedor");
            System.out.println("5. Consultar proveedor");
            System.out.println("6. Eliminar proveedor");
            System.out.println("7. Añadir producto");
            System.out.println("8. Modificar producto");
            System.out.println("9. Consultar producto");
            System.out.println("10. Eliminar producto");
            System.out.println("11. Añadir cliente");
            System.out.println("12. Modificar cliente");
            System.out.println("13. Consultar cliente");
            System.out.println("14. Eliminar cliente");
            System.out.println("15. Salir");
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
                    modifySupplier();
                    break;
                case "5":
                    showSupplier();
                    break;
                case "6":
                    deleteSupplier();
                    break;
                case "7":
                    addProduct();
                    break;
                case "8":
                    modifyProduct();
                    break;
                case "9":
                    showProduct();
                    break;
                case "10":
                    deleteProduct();
                    break;
                case "11":
                    addClient();
                    break;
                case "12":
                    modifyClient();
                    break;
                case "13":
                    showClient();
                    break;
                case "14":
                    deleteClient();
                    break;
            }
        } while (!choice.equals("14"));

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

    private void modifySupplier() {

    }

    private void showSupplier() {

    }

    private void deleteSupplier() {

    }

    private void addProduct() {
        System.out.println("Nombre: ");
        String name = keyboard.nextLine();
        System.out.println("Precio: ");
        float price = Float.parseFloat(keyboard.nextLine());
        System.out.println("Stock: ");
        int stock = Integer.parseInt(keyboard.nextLine());
        System.out.println("Proveedor: ");
        String supplier = keyboard.nextLine();
        Product product = new Product(name.trim(), price, stock, supplier.trim());

        ProductDao productDao = new ProductDao(connection);
        productDao.addProduct(product);
        System.out.println("El producto se ha añadido correctamente");
    }

    private void modifyProduct() {

    }

    private void showProduct() {

    }

    private void deleteProduct() {

    }

    private void addClient() {
        System.out.println("Nombre: ");
        String name = keyboard.nextLine();
        System.out.println("Apellidos ");
        String surname = keyboard.nextLine();
        System.out.println("DNI: ");
        String dni = keyboard.nextLine();
        System.out.println("Telefono: ");
        String phone = keyboard.nextLine();
        System.out.println("E-mail: ");
        String email = keyboard.nextLine();
        Client client = new Client(name.trim(), surname.trim(), dni.trim(), phone.trim(), email.trim());

        ClientDao clientDao = new ClientDao(connection);
        clientDao.addClient((client));
        System.out.println("El cliente se ha añadido correctamente");
    }


    private void modifyClient() {

    }

    private void showClient() {

    }

    private void deleteClient() {
    }

}
