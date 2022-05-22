package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.Database;
import com.svalero.cesped.dao.SupplierDao;
import com.svalero.cesped.domain.Supplier;
import com.svalero.cesped.domain.User;
import com.svalero.cesped.exception.SupplierAlreadyExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/add-supplier")
public class AddSupplierServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("AccesoDenegado.jsp");
        }

        String nombre = request.getParameter("nombre");
        String cif = request.getParameter("cif");
        String phone = request.getParameter("telefono");
        String email = request.getParameter("email");
        Supplier supplier = new Supplier(nombre, cif, phone, email); //creo el proveedor

        Database database = new Database();
        SupplierDao supplierDao = new SupplierDao(database.getConnection());
        try {
            supplierDao.addSupplier(supplier);
            out.println("<div class='alert alert-success' role='alert'>El proveedor se ha añadido correctamente</div>");
        } catch (SupplierAlreadyExistException saee) {
            out.println("<div class='alert alert-warning' role='alert'>El proveedor ya está registrado en el sistema.</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al registrar el cliente. Intentalo más tarde</div>");
            sqle.printStackTrace(); //TODO QUITAR DE LA VERSION FINAL
        }

    }
}