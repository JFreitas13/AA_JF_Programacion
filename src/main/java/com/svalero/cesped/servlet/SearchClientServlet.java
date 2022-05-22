package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.ClientDao;
import com.svalero.cesped.dao.Database;
import com.svalero.cesped.domain.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/search-clients")
public class SearchClientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchText = request.getParameter("searchtext");

        Database database = new Database();
        ClientDao clientDao = new ClientDao(database.getConnection());
        try {
            ArrayList<Client> clients = clientDao.findAllClient(searchText);
            StringBuilder result = new StringBuilder("<ul class='list-group");
            for (Client client : clients) {
                result.append("<li class='list-group-item'>").append((client.getName() + " " + client.getSurname())).append("</li>");
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
