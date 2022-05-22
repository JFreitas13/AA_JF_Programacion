<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"

         import="com.svalero.cesped.domain.User"
%>

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
                $.post("add-client", formValue, function (data) { <!-- servlet que recibe todos los datos del formulario -->
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
                <input name="nombre" type="text" class="form-control w-25" id="nombre">
                <!-- input name es lo importante para poder coger las variables con java -->
            </div>
            <div class="mb-3">
                <label for="apellidos" class="form-label">Apellidos</label>
                <input name="apellidos" type="text" class="form-control w-25" id="apellidos">
            </div>
            <div class="mb-2">
                <label for="dni" class="form-label">DNI</label>
                <input name="dni" type="text" class="form-control w-25" id="dni">
            </div>
            <div class="mb-2">
                <label for="telefono" class="form-label">Telefono</label>
                <input name="telefono" type="text" class="form-control w-25" id="telefono">
            </div>
            <div class="mb-2">
                <label for="email" class="form-label">Correo Electrónico</label>
                <input name="email" type="text" class="form-control w-25" id="email">
            </div>

            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>
        <div id="result"></div>

    </div>
</body>
</html>