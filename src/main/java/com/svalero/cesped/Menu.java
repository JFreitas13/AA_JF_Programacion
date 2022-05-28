package com.svalero.cesped;

import com.svalero.cesped.dao.*;
import com.svalero.cesped.domain.Client;
import com.svalero.cesped.domain.Product;
import com.svalero.cesped.domain.Supplier;
import com.svalero.cesped.exception.ClientAlreadyExistException;
import com.svalero.cesped.exception.SupplierAlreadyExistException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner keyboard;
    private Database database;
    private Connection connection;


    public Menu() {
        keyboard = new Scanner(System.in);
    }

    //motodo para conectar a la BBDD
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
                /*case "9":
                    showProduct();
                    break;*/
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
                /*case "14":
                    deleteClient();
                    break;*/
            }
        } while (!choice.equals("15"));

    }

    //añadir pedido
    private void addOrder() {

        System.out.println("Indica que modelos quieres comprar. Separados por comas");
        String orderProducts = keyboard.nextLine();

      try {
            String[] productArray = orderProducts.split(","); //devuelve un array y en cada posición en un dato
            ProductDao productDao = new ProductDao(connection);
            List<Product> products = new ArrayList<>();
            for (String productID: productArray) {
                Product product = productDao.findByName(productID.trim()).get();       // TODO Faltaría mejorar esto para comprobar valores no válidos
                products.add(product);
            }

            OrderDao orderDao = new OrderDao(connection);
            //orderDao.add();
            System.out.println("El pedido se ha creado correctamente");
        } catch (SQLException sqle) {
            System.out.println("No se ha podido comunicar con la base de datos. Inténtelo de nuevo");
            sqle.printStackTrace();
        }
    }

    //listado de pedidos
    private void showOrder(){

    }

    //añadir proveedor
    private void addSupplier() {
        SupplierDao supplierDao = new SupplierDao(connection); //dar de alta en BBDD

        System.out.println("Nombre Comercial: ");
        String name = keyboard.nextLine();
        System.out.println("CIF: ");
        String cif = keyboard.nextLine();
        System.out.println("Telefono: ");
        String phone = keyboard.nextLine();
        System.out.println("E-mail: ");
        String email = keyboard.nextLine();
        Supplier supplier = new Supplier(name.trim(), cif.trim(), phone.trim(), email.trim()); //crear el nuevo objeto proveedor con los datos introducidos

        try {
            supplierDao.addSupplier(supplier);
            System.out.println("Rl proveedor se ha añadido correctamente");
        } catch (SupplierAlreadyExistException saee) {
            System.out.println(saee.getMessage());
            saee.printStackTrace(); //borrar de la version final
        } catch (SQLException sqle) {
            System.out.println("No se ha podido añadir el proveedor. Intentalo más tarde.");
            sqle.printStackTrace(); //borrar de la version final
        }
    }

    //REVISAR
    private void modifySupplier() {
        SupplierDao supplierDao = new SupplierDao(connection);
        Supplier supplier;

        System.out.println("Introduce el ID del proveedor que deseas moficiar: ");
        String idSupplier = keyboard.nextLine();
        addSupplier();

       /* try {
            supplierDao.findByCif(Integer.parseInt(idSupplier));

            boolean modified = supplierDao.modifySupplier(idSupplier, supplier);
            if (modified) {
                System.out.println("Proveedor modificado correctamente");
            } else {
                System.out.println("No ha sido posible modificar el proveedor. Intentalo más tarde.");
            }
        } catch (SQLException sqle) {
            System.out.println("Error de conexión. Verifica que los datos son correctos.");
            sqle.printStackTrace();
        }*/
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
            sqlException.printStackTrace(); //quitar de la version final
        }
    }

    private void deleteSupplier() {
        System.out.println("Indicar CIF del proveedor a eliminar: ");
        String cif = keyboard.nextLine();
        SupplierDao supplierDao = new SupplierDao(connection);
        try {
            boolean deleted = supplierDao.deleteSupplier(cif);
            if (deleted)
                System.out.println("El proveedor se ha eliminado correctamente");
            else
                System.out.println("El proveedor no se ha podido eliminar o no existe");
        } catch (SQLException sqle) {
            System.out.println("Error de conexión. Verifica que los datos son correctos.");
        }
    }

    private void addProduct() {
     /*   System.out.println("Nombre: ");
        String name = keyboard.nextLine();
        System.out.println("Precio: ");
        float price = Float.parseFloat(keyboard.nextLine());
        System.out.println("Stock: ");
        int stock = Integer.parseInt(keyboard.nextLine());
        System.out.println("Proveedor: ");
        String supplier = keyboard.nextLine();
        Product product = new Product(name.trim(), price, stock, supplier);

        ProductDao productDao = new ProductDao(connection);
        try {
            productDao.add(product);
            System.out.println("El producto se ha añadido correctamente");
        } catch (SQLException sqle) {
            System.out.println("No se ha podido añadir el producto. Intentalo más tarde.");
        }*/
    }

    private void modifyProduct() {

    }

   /* private void showProduct() {
        ProductDao productDao = new ProductDao(connection);

        System.out.println("Lista de productos");
        try {
            ArrayList<Product> products = productDao.findAll();
            for (Product product : products) {
                System.out.println(product.getIdProduct() + " | " + product.getName() + " | " + product.getPrice() + " | " + product.getStock()+ " | " + product.getSupplier());
            }
        } catch (SQLException sqlException) {
            System.out.println("Error de conexión. Verifica que los datos son correctos.");
            sqlException.printStackTrace(); //quitar de la version final
        }

    }*/

    //rehacer con id
    private void deleteProduct() {
        System.out.println("Indicar nombre del producto a eliminar: ");
        String name = keyboard.nextLine();
        ProductDao productDao = new ProductDao(connection);
        try {
            boolean deleted = productDao.delete(name);
            if (deleted)
                System.out.println("El producto se ha eliminado correctamente");
            else
                System.out.println("El producto no se ha podido eliminar o no existe");
        } catch (SQLException sqle) {
            System.out.println("Error de conexión. Verifica que los datos son correctos.");
        }
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
        } catch (ClientAlreadyExistException caee){
            System.out.println(caee.getMessage()); //coge mensaje personalizado de la excepcion
            caee.printStackTrace(); // quitar de la version final. Sirve para ver la traza de la excepcion
        } catch (SQLException sqle) {
            System.out.println("No se ha podido añadir el cliente. Intentalo más tarde");
            sqle.printStackTrace(); // quitar de la version final. Sirve para ver la traza de la excepcion
        }
    }


    private void modifyClient() {

    }

    private void showClient() {
        ClientDao clientDao = new ClientDao(connection);

        System.out.println("Lista de clientes");
       /* try {
            ArrayList<Client> clients = clientDao.findAllClient();
            for (Client client : clients) {
                System.out.println(client.getIdClient() + " | " + client.getName() + " | " + client.getSurname() + " | " + client.getDni() + " | " + client.getPhone() + " | " + client.getEmail());
            }
        } catch (SQLException sqlException) {
            System.out.println("Error de conexión. Verifica que los datos son correctos.");
            sqlException.printStackTrace(); //quitar de la version final
        }*/
    }

    //rehacer con id
    /*private void deleteClient() {
        System.out.println("Indicar DNI del cliente a eliminar: ");
        String dni = keyboard.nextLine();
        ClientDao clientDao = new ClientDao(connection);
        try {
            boolean deleted = clientDao.deleteClient();
            if (deleted)
                System.out.println("El cliente se ha eliminado correctamente");
            else
                System.out.println("El cliente no se ha podido eliminar o no existe");
        } catch (SQLException sqle) {
            System.out.println("Error de conexión. Verifica que los datos son correctos.");
        }
    }*/
}
