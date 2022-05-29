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
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); //recuperar sesion
        session.removeAttribute("currrentUser");
        response.sendRedirect("login.jsp");
    }
}
