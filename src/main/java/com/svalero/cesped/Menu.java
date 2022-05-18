package com.svalero.cesped;

import com.svalero.cesped.dao.*;
import com.svalero.cesped.domain.Client;
import com.svalero.cesped.domain.Product;
import com.svalero.cesped.domain.Supplier;
import com.svalero.cesped.exception.ClientAlreadyExistException;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner keyboard;
    private Database database;
    private Connection connection;


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
        OrderDao orderDao = new OrderDao(connection);

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
        System.out.println("Telefono: ");
        String phone = keyboard.nextLine();
        System.out.println("E-mail: ");
        String email = keyboard.nextLine();
        Supplier supplier = new Supplier(name.trim(), cif.trim(), phone.trim(), email.trim()); //crear el nuevo objeto proveedor con los datos introducidos

        SupplierDao supplierDao = new SupplierDao(connection); //dar de alta en BBDD
        supplierDao.addSupplier(supplier);
        System.out.println("El proveedor se ha añadido correctamente");

    }

    private void modifySupplier() {
        SupplierDao supplierDao = new SupplierDao(connection);
        Supplier supplier = new Supplier("b", "12345678Y", "61541408", "j@jk.com");

        System.out.println("Introduce el ID del proveedor que deseas moficiar: ");
        String idSupplier = keyboard.nextLine();
        addSupplier();

        try {
            supplierDao.findByCif(Integer.parseInt(idSupplier));

            boolean modified = supplierDao.modify(idSupplier, supplier);
            if (modified) {
                System.out.println("Proveedor modificado correctamente");
            } else {
                System.out.println("No ha sido posible modificar el proveedor. Intentalo más tarde.");
            }
        } catch (SQLException sqle) {
            System.out.println("Error de conexión. Verifica que los datos son correctos.");
            sqle.printStackTrace();
        }
    }

    private void showSupplier() {
        SupplierDao supplierDao = new SupplierDao(connection);

        System.out.println("Lista de proveedores");
        try {
            ArrayList<Supplier> suppliers = supplierDao.findAll();
            for (Supplier supplier : suppliers) {
                System.out.println(supplier.getId() + " | " + supplier.getName() + " | " + supplier.getCif() + " | " + supplier.getPhone() + " | " + supplier.getEmail());
            }
        } catch (SQLException sqlException) {
            System.out.println("Error de conexión. Verifica que los datos son correctos.");
            sqlException.printStackTrace();
        }

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
        try {
            productDao.addProduct(product);
            System.out.println("El producto se ha añadido correctamente");
        } catch (SQLException sqle) {
            System.out.println("No se ha podido añadir el producto. Intentalo más tarde.");
        }
    }

    private void modifyProduct() {

    }

    private void showProduct() {

    }

    private void deleteProduct() {

    }

    private void addClient() {
        ClientDao clientDao = new ClientDao(connection);

        System.out.println("Nombre: ");
        String name = keyboard.nextLine();
        System.out.println("Apellidos: ");
        String surname = keyboard.nextLine();
        System.out.println("DNI: ");
        String dni = keyboard.nextLine();
        System.out.println("Telefono: ");
        String phone = keyboard.nextLine();
        System.out.println("E-mail: ");
        String email = keyboard.nextLine();
        Client client = new Client(name.trim(), surname.trim(), dni.trim(), phone.trim(), email.trim());


        try {
            clientDao.addClient((client));
            System.out.println("El cliente se ha añadido correctamente.");
        } catch (ClientAlreadyExistException | SQLException caee) {
            System.out.println("No se ha podido añadir el cliente. Intentalo más tarde");
            caee.printStackTrace(); // quitar de la version final. Sirve para ver la traza de la excepcion
        }
    }


    private void modifyClient() {

    }

    private void showClient() {

    }

    private void deleteClient() {
    }

}
