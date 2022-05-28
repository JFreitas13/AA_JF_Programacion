<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"

         import="com.svalero.cesped.domain.User"
         import="com.svalero.cesped.dao.Database"
         import="com.svalero.cesped.dao.ClientDao"
         import="com.svalero.cesped.domain.Client"
         import="java.util.List"
         import="java.sql.SQLException"
%>

<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("AccesoDenegado.jsp");
    }
%>
<html lang="es">
<head>
    <!-- Para usar la hoja de estilos de  Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Listado de clientes</title>
</head>
<body>
<div class="container">
    <h2>Listado de clientes</h2>
    <div class="alert alert-secondary" role="alert">
        Para ver información más detallada del cliente pincha en él.
    </div>
    <ul class="list-group">
        <%
            // Acceder a la base de datos y recuperar la información de los clientes
            Database database = new Database();
            ClientDao clientDao = new ClientDao(database.getConnection());
            try {
                List<Client> clients = clientDao.findAllClient();
                for (Client client : clients) {
        %>
                    <li class="list-group-item">
                    <a target="_blank" href="client.jsp?idClient=<%= client.getIdClient() %>"><%= client.getName() + " " + client.getSurname()  %></a>
                    </li>
        <%
            }
        } catch (SQLException sqle) {

        %>
        <div class="alert alert-danger" role="alert">
            <h6>Error de conexión con la base da datos.</h6>
        </div>
        <%
                sqle.printStackTrace();
            }
        %>
    </ul>
    <a href="index.jsp" class="btn btn-primary">Volver</a>
</div>
</body>
</html>
