package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.Database;
import com.svalero.cesped.dao.ProductDao;
import com.svalero.cesped.dao.SupplierDao;
import com.svalero.cesped.domain.Product;
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

@WebServlet("/search-products")
public class SearchProductsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchText = request.getParameter("searchtext");

        Database database = new Database();
        ProductDao productDao = new ProductDao(database.getConnection());
        try {
            ArrayList<Product> products = productDao.findAllProduct(searchText);
            StringBuilder result = new StringBuilder("<ul class='list-group");
            for (Product product : products) {
                result.append("<li class='list-group-item'>").append((product.getName())).append("</li>");
            }
            result.append("</ul>");
            out.println(result);
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Error de conexión a BBDD</div>");
            sqle.printStackTrace(); //TODO QUITAR ESTO DE LA VERSION FINAL
        }
    }
}
//TODO TERMINAR