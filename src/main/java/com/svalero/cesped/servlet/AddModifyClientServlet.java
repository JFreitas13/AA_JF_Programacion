package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.ClientDao;
import com.svalero.cesped.dao.Database;
import com.svalero.cesped.domain.Client;
import com.svalero.cesped.domain.User;
import com.svalero.cesped.exception.ClientAlreadyExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/add-modify-client")
public class AddModifyClientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("AccesoDenegado.jsp");
        }

        String nombre = request.getParameter("nombre");
        String surname = request.getParameter("apellidos");
        String dni = request.getParameter("dni");
        String phone = request.getParameter("telefono");
        String email = request.getParameter("email");
        String action = request.getParameter("action");
        String clientId = request.getParameter("clientId");
        Client client = new Client(nombre, surname, dni, phone, email); //creo el cliente

        Database database = new Database();
        ClientDao clientDao = new ClientDao(database.getConnection());
        try {
            if (action.equals("register")) {
                clientDao.addClient(client);
                out.println("<div class='alert alert-success' role='alert'>El cliente se ha añadido correctamente</div>");
            } else {
                clientDao.modifyById(Integer.parseInt(clientId), client); //paso id y nuevo cliente creado arriba
                out.println("<div class='alert alert-success' role='alert'>El cliente se ha modificado correctamente</div>");
            }
        } catch (ClientAlreadyExistException caee) {
            out.println("<div class='alert alert-warning' role='alert'>El cliente ya está registrado en el sistema.</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al registrar el cliente. Intentalo más tarde</div>");
            sqle.printStackTrace(); //TODO QUITAR DE LA VERSION FINAL
        }

    }
}
