<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"

         import="com.svalero.cesped.domain.User"
%>
<%@ page import="com.svalero.cesped.domain.Client" %>
<%@ page import="com.svalero.cesped.dao.Database" %>
<%@ page import="com.svalero.cesped.dao.ClientDao" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>

<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("AccesoDenegado.jsp");
    }

    //cojo el parametro
    String textBouton = "";
    String clientId = request.getParameter("idClient");
    Client client = null;
    if (clientId != null) {
        textBouton = "Modificar";
        Database database = new Database();
        ClientDao clientDao = new ClientDao(database.getConnection());
        try {
            Optional<Client> optionalClient = clientDao.findById(Integer.parseInt(clientId)); //recuperar un cliente con determinado DNI
            client = optionalClient.get();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    } else {
        textBouton = "Registrar";
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
                $.post("add-modify-client", formValue, function (data) { <!-- servlet que recibe todos los datos del formulario -->
                    $("#result").html(data); <!-- Lo usamos para enviar la respuesta al div en la misma página -->
                });
            });
        });
    </script>

    <div class="container">
        <h2>Añadir cliente</h2>

        <form>
            <div class="mb-2">
                <label for="nombre" class="form-label">Nombre</label>
                <input name="nombre" type="text" class="form-control w-25" id="nombre" value="<% if (client != null) out.print(client.getName()); %>">
                <!-- input name es lo importante para poder coger las variables con java -->
            </div>
            <div class="mb-3">
                <label for="apellidos" class="form-label">Apellidos</label>
                <input name="apellidos" type="text" class="form-control w-25" id="apellidos" value="<% if (client != null) out.print(client.getSurname()); %>">
            </div>
            <div class="mb-2">
                <label for="dni" class="form-label">DNI</label>
                <input name="dni" type="text" class="form-control w-25" id="dni" value="<% if (client != null) out.print(client.getDni()); %>">
            </div>
            <div class="mb-2">
                <label for="telefono" class="form-label">Telefono</label>
                <input name="telefono" type="text" class="form-control w-25" id="telefono" value="<% if (client != null) out.print(client.getPhone()); %>">
            </div>
            <div class="mb-2">
                <label for="email" class="form-label">Correo Electrónico</label>
                <input name="email" type="text" class="form-control w-25" id="email" value="<% if (client != null) out.print(client.getEmail()); %>">
            </div>
            <input type="hidden" name="action" value="<% if (client != null) out.print("modify"); else out.print("register"); %>">
            <input type="hidden" name="clientId" value="<% if (client != null) out.print(client.getIdClient()); %>"> <!--campo oculto. Enviar valor definido internamente-->
            <button type="submit" class="btn btn-primary"><%= textBouton %></button>
        </form>
        <div id="result"></div>
        <a href="index.jsp" class="btn btn-primary">Volver</a>

    </div>
</body>
</html>