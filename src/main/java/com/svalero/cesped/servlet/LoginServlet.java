package com.svalero.cesped.servlet;

import com.svalero.cesped.dao.Database;
import com.svalero.cesped.dao.UserDao;
import com.svalero.cesped.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/login") //porque en el action del fomr dije que la action es login
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //recoger valor que me han pasado desde el formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //comprobar si existe alguien con este usuario y contraseña. Necesito user dao y conectar BBDD
        Database database = new Database();
        UserDao userDao = new UserDao(database.getConnection());
        try {
            Optional<User> user = userDao.login(username, password);
            //saber si hay o no usuario
            if (user.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser", user.get()); //es lo que me permite coger siempre el usuario que esta logueado o no
                response.sendRedirect("index.jsp");
                System.out.println("Sesión iniciada correctamente");
            } else {
                response.sendRedirect("loginerror.jsp");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
