package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.Database;
import com.svalero.cesped.dao.SupplierDao;
import com.svalero.cesped.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/delete-supplier")
public class DeleteSupplierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("AccesoDenegado.jsp");
        }
        String cif = request.getParameter("cif");

        Database database = new Database();
        SupplierDao supplierDao = new SupplierDao(database.getConnection());
        try {
            supplierDao.deleteSupplier(cif);
            out.println("<div class='alert alert-success' role='alert'>El proveedor se ha borrado correctamente</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al eliminar el proveedor. Intentalo más tarde</div>");
            sqle.printStackTrace(); //TODO BORRAR DE LA VERSION FINAL

       /* String idSupplier = request.getParameter("id_proveedor");

        Database database = new Database();
        SupplierDao supplierDao = new SupplierDao(database.getConnection());
        try {
            supplierDao.deleteById(Integer.parseInt(idSupplier));
            out.println("<div class='alert alert-success' role='alert'>El proveedor se ha borrado correctamente</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al registrar el cliente. Intentalo más tarde</div>");
            sqle.printStackTrace(); //TODO BORRAR DE LA VERSION FINAL
        }*/
        }
    }
}
