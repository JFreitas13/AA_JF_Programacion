<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"

    //import="com.svalero.amazonwebapp.domain.User"
%>

<jsp:include page="header.jsp" />

<body>
    <div class="container">
        <h2>Amazon Book Store</h2>
        <ul>
        <li><a href="/amazon/books.jsp">Ver Libros</a></li>
        <li><a href="/amazon/orders.jsp">Ver Pedidos</a></li>
        <li><a href="/amazon/searchbook.jsp">Buscar Libros</a></li>
        <%
            if ((currentUser != null) && (currentUser.getRole().equals("admin"))) {
        %>
                <li><a href="/amazon/addbook.jsp">Registrar un libro</a></li>
        <%
            }
            if (currentUser != null) {
        %>
                <li><a href="/amazon/logout">Cerrar sesión</a></li>
        <%
            }
        %>
        </ul>
        <br/>
        <div class="alert alert-success" role="alert">
          Bienvenido <% if (currentUser != null) out.print(currentUser.getName()); %>
        </div>
    </div>
</body>
</html>