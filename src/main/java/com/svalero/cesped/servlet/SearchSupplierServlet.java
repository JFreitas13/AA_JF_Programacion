package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.Database;
import com.svalero.cesped.dao.SupplierDao;
import com.svalero.cesped.domain.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/search-suppliers")
public class SearchSupplierServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchText = request.getParameter("searchtext");

        Database database = new Database();
        SupplierDao supplierDao = new SupplierDao(database.getConnection());
        try {
            ArrayList<Supplier> suppliers = supplierDao.findAllSupplier(searchText);
            StringBuilder result = new StringBuilder("<ul class='list-group");
            for (Supplier supplier : suppliers) {
                result.append("<li class='list-group-item'>").append((supplier.getName())).append("</li>");
            }
            result.append("</ul>");
            out.println(result);
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Cliente no encontrado. Verifica que los datos son correctos</div>");
            sqle.printStackTrace(); //TODO QUITAR ESTO DE LA VERSION FINAL
        }
    }
}

//TODO MEJORAR BUSCADOR
