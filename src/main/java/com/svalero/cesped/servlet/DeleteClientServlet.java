package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.ClientDao;
import com.svalero.cesped.dao.Database;
import com.svalero.cesped.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/delete-client")
public class DeleteClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("AccesoDenegado.jsp");
        }

        String clientId = request.getParameter("idClient");

        Database database = new Database();
        ClientDao clientDao = new ClientDao(database.getConnection());
        try {
            clientDao.deleteById(Integer.parseInt(clientId));
            out.println("<div class='alert alert-success role='alert'>El cliente se ha borrado correctamente</div> \n <a href='showclients.jsp' class='btn btn-primary'>Listado Clientes</a>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al borrar el cliente. Intentalo más tarde</div>");
            sqle.printStackTrace(); //TODO QUITAR DE LA VERSION FINAL
        }
    }
}

