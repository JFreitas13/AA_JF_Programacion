<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"

         import="com.svalero.cesped.domain.User"
         import="com.svalero.cesped.dao.Database"
         import="com.svalero.cesped.dao.ClientDao"
         import="com.svalero.cesped.domain.Client"
         import="java.util.List"
%>
<%@ page import="java.util.Optional" %>
<%@ page import="jdk.internal.jimage.ImageReaderFactory" %>
<%@ page import="java.sql.SQLException" %>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("AccesoDenegado.jsp");
    }
%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <%
        String clientDni = request.getParameter("dni");
        Database database = new Database();
        ClientDao clientDao = new ClientDao((database.getConnection()));
        Client client = null;
        try {
            Optional<Client> optionalClient = clientDao.findByDni(clientDni);
            client = optionalClient.get();

    %>
    <div class="container">
        <div class="card text-center">
            <div class="card-header">Información cliente</div>
            <div class="card-body">
                <h5 class="card-title"><%= client.getName() + " " + client.getSurname() %></h5>
                <h5 class="card-title"><%= client.getPhone() %></h5>
                <h5 class="card-title"><%= client.getDni() %></h5>
                <a href="client.jsp?phone=<%= client.getPhone() %>" class="btn btn-primary">Llamar</a>
                <a href="deleteclient.jsp?dni=<%= client.getDni() %>" class="btn btn-outline-danger">Eliminar</a>
            </div>
        </div>
    </div>
    <%
    } catch (SQLException sqle) {
        %>
        <div class='alert alert-danger' role='alert'>Se ha producido un error. intentalo más tarde</div>
        <%
            }
        %>
</body>
</html>