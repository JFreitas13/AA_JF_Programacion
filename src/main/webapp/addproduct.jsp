<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"

         import="com.svalero.cesped.domain.User"
%>
<%@ page import="com.svalero.cesped.dao.SupplierDao" %>
<%@ page import="com.svalero.cesped.dao.Database" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.svalero.cesped.domain.Supplier" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>

<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("AccesoDenegado.jsp");
    }

    String supplierId = request.getParameter("id");
    Database database = new Database();
    SupplierDao supplierDao = new SupplierDao((database.getConnection()));
    Supplier supplier = null;
    try {
        Optional<Supplier> optionalSupllier = supplierDao.findById(Integer.parseInt(supplierId));
        supplier = optionalSupllier.get();
    } catch (SQLException sqle) {
        sqle.printStackTrace();
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
                $.post("add-product", formValue, function (data) { <!-- servlet que recibe todos los datos del formulario -->
                    $("#result").html(data); <!-- Lo usamos para enviar la respuesta al div en la misma página -->
                });
            });
        });
    </script>

    <div class="container">
        <h2>Añadir producto</h2>

        <form>
            <div class="mb-2">
                <label for="nombre" class="form-label">Nombre del producto</label>
                <input name="nombre" type="text" class="form-control w-25" id="nombre">
                <!-- input name es lo importante para poder coger las variables con java -->
            </div>
            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input name="precio" type="text" class="form-control w-25" id="precio">
            </div>
            <div class="mb-2">
                <label for="stock" class="form-label">Stock</label>
                <input name="stock" type="text" class="form-control w-25" id="stock">
            </div>
            <!--<div class="mb-2">
                <label for="idproveedor" class="form-label">Id proveedor</label>
                <input name="idproveedor" type="text" class="form-control w-25" id="idproveedor">
            </div>-->
            <%
                //Database database = new Database();
                //Connection connection = database.getConnection();
            %>
            <div class="form-label">
                <label for="idProveedor">Proveedor</label>
                <select class="form-control w-25" id="idProveedor" name="idProveedor" value="<% supplier.getId(); %>">
                    <option>Selecciona un Proveedor</option>
                    <%
                        //SupplierDao supplierDao = new SupplierDao(database.getConnection());
                        ArrayList<Supplier> suppliers = supplierDao.findAllSupplier();
                        for (Supplier supplier1r : suppliers) {
                            out.println("<option value=\"" + supplier.getId() + "\">" + supplier.getName() + "</option>");
                        }
                    %>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>
        <div id="result"></div>
    </div>

</body>
</html>