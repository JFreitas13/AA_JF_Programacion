package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.Database;
import com.svalero.cesped.dao.ProductDao;
import com.svalero.cesped.dao.SupplierDao;
import com.svalero.cesped.domain.Product;
import com.svalero.cesped.domain.Supplier;
import com.svalero.cesped.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("AccesoDenegado.jsp");
        }

        //Product product = new Product();

        String nombre = request.getParameter("nombre");
        float precio = Float.parseFloat(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int idProveedor = Integer.parseInt(request.getParameter("id_proveedor"));

        Product product = new Product(nombre, precio, stock, idProveedor);

        Database database = new Database();
        ProductDao productDao = new ProductDao(database.getConnection());
        try {
            productDao.add(product);
            out.println("<div class='alert alert-success' role='alert'>El producto se ha añadido correctamente</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-success' role='alert'>proveedor no encontrado</div>");
            sqle.printStackTrace();

        }
    }
}

/*Database database = new Database();
        SupplierDao supplierDao = new SupplierDao(database.getConnection());
        try {
          supplierDao.findByCif(proveedor);
          out.println("<div class='alert alert-success' role='alert'>El producto se ha añadido correctamente</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-success' role='alert'>proveedor no encontrado</div>");
            sqle.printStackTrace();
        }*/