<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"

         import="com.svalero.cesped.domain.User"
         import="com.svalero.cesped.dao.Database"

%>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.cesped.domain.Supplier" %>
<%@ page import="com.svalero.cesped.dao.SupplierDao" %>
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
        String supplierCif = request.getParameter("cif");
        Database database = new Database();
        SupplierDao supplierDao = new SupplierDao((database.getConnection()));
        Supplier supplier = null;
        try {
            Optional<Supplier> optionalSupplier = supplierDao.findByCif(supplierCif);
            supplier = optionalSupplier.get();

    %>
    <div class="container">
        <div class="card text-center">
            <div class="card-header">Información cliente</div>
            <div class="card-body">
                <h5 class="card-title"><%= supplier.getName()%></h5>
                <h5 class="card-title"><%= supplier.getPhone() %></h5>
                <a href="client.jsp?dni=<%= supplier.getCif() %>" class="btn btn-primary">Llamar</a>
                <a href="delete-supplier?cif=<%= supplier.getCif() %>" class="btn btn-outline-danger">Eliminar</a>
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