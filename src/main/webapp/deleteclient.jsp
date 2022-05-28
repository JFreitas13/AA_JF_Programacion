<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"

         import="com.svalero.cesped.domain.User"
%>
<%@ page import="com.svalero.cesped.dao.Database" %>
<%@ page import="com.svalero.cesped.dao.ClientDao" %>
<%@ page import="com.svalero.cesped.domain.Client" %>
<%@ page import="java.util.Optional" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- libreria de ajax es como una libreria de js con cosas ya hechas -->
</head>
<body>
    <script type="text/javascript">
        $(document).ready(function () {
            $("form").on("submit", function (event) {
                event.preventDefault();
                var formValue = $(this).serialize();
                $.post("delete-client", formValue, function (data) { <!-- servlet que recibe todos los datos del formulario -->
                    $("#result").html(data); <!-- Lo usamos para enviar la respuesta al div en la misma página -->
                });
            });
        });
    </script>
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
        <h2>Eliminar Cliente</h2>
        <div class="card text-center">
            <div class="card-header">¿Estás seguro que quieres eliminar el cliente?</div>
            <div class="card-body">
                <a href="delete-client?idClient=<%= client.getIdClient() %>" class="btn btn-danger">Si</a>
                <a href="showclients.jsp?idClient<%= client.getIdClient() %>" class="btn btn-outline-danger">No</a>
            </div>
        </div>
    </div>
        <div id="result"></div>
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