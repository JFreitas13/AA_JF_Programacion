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

        String supplierId = request.getParameter("id");

        Database database = new Database();
        SupplierDao supplierDao = new SupplierDao(database.getConnection());
        try {
            supplierDao.deleteById(Integer.parseInt(supplierId));
            out.println("<div class='alert alert-success' role='alert'>El proveedor se ha borrado correctamente</div> \n <a href='showsuppliers.jsp' class='btn btn-primary'>Listado Proveedores");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>No se ha podido conectar con la base de datos. Verifique que todos los datos son correctos.</div>");
            sqle.printStackTrace(); //TODO BORRAR DE LA VERSION FINAL

        }
    }
}
