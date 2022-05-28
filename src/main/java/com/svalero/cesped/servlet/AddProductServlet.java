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
import java.util.Optional;

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
        //String nombre = request.getParameter("nombre");
        //float precio = Float.parseFloat(request.getParameter("precio"));
        //int stock = Integer.parseInt(request.getParameter("stock"));
        String supplierId = request.getParameter("id");

        Database database = new Database();
        SupplierDao supplierDao = new SupplierDao(database.getConnection());
        try {
            Optional<Supplier> supplier = supplierDao.findById(Integer.parseInt(supplierId));

            Product product = new Product();
            product.setName(request.getParameter("name"));
            product.setPrice(Float.parseFloat(request.getParameter("price")));
            product.setStock(Integer.parseInt(request.getParameter("stock")));

            ProductDao productDao = new ProductDao(database.getConnection());
            productDao.add(supplierId, product);
            out.println("<div class='alert alert-success' role='alert'>producto añadido correctamente</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-success' role='alert'>Error de conexión.</div>");
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