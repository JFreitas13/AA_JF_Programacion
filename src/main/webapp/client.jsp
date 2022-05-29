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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Información Cliente</title>
</head>
<body>
    <%
        String clientId = request.getParameter("idClient");
        Database database = new Database();
        ClientDao clientDao = new ClientDao((database.getConnection()));
        Client client = null;
        try {
            Optional<Client> optionalClient = clientDao.findById(Integer.parseInt(clientId));
            client = optionalClient.get();
    %>
    <div class="container">
        <div class="card text-center">
            <div class="card-header">Información cliente</div>
            <div class="card-body">
                <h5 class="card-title"><%= client.getName() + " " + client.getSurname() %></h5>
                <h6 class="card-subtitle mb-2 text-muted">Telefono: <%= client.getPhone() %></h6>
                <h6 class="card-subtitle mb-2 text-muted">DNI: <%= client.getDni() %></h6>
                <a href="client.jsp?phone=<%= client.getPhone() %>" class="btn btn-outline-primary">Llamar</a>
                <a href="addclient.jsp?idClient=<%= client.getIdClient() %>" class="btn btn-outline-warning">Modificar</a>
                <a href="deleteclient.jsp?idClient=<%= client.getIdClient() %>" class="btn btn-outline-danger">Eliminar</a>
            </div>
            <a href="showclients.jsp" class="btn btn-primary">Volver</a>
        </div>
    </div>
    <%
    } catch (SQLException sqle) {
        %>
        <div class='alert alert-danger' role='alert'>No se ha podido conectar con la base de datos. Verifique que todos los datos con correctos.</div>
        <%
            }
        %>
</body>
</html>