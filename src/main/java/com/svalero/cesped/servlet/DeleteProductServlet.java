package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.ClientDao;
import com.svalero.cesped.dao.Database;
import com.svalero.cesped.dao.ProductDao;
import com.svalero.cesped.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/delete-product")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("AccesoDenegado.jsp");
        }

        String productId = request.getParameter("idProduct");

        Database database = new Database();
        ProductDao productDao = new ProductDao(database.getConnection());
        try {
            productDao.deleteById(Integer.parseInt(productId));
            out.println("<div class='alert alert-success role='alert'>El producto se ha borrado correctamente</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Error de conexion</div>");
            sqle.printStackTrace(); //TODO QUITAR DE LA VERSION FINAL
        }
    }
}

