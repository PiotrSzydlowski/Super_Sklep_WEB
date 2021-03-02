<%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@page import="entity.User" %>
<%
    User user1 = (User) session.getAttribute("current-user");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-bg">
    <a class="navbar-brand" href="index.jsp">Super Sklep</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Strona główna <span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">

                <li class="nav-item active">
                    <a class="nav-link" data-toggle="modal" data-target="#cart"><i class="fa fa-cart-plus" style="font-size: 25px"></i>
                        <span class="ml-0 mt-0 cart-items" style="font-size: 15px"></span></a>
                </li>
                <%
                    if (user1 == null) {
                %>
                <li class="nav-item active">
                    <a class="nav-link" href="login.jsp">Zaloguj</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="register.jsp">Utwórz konto</a>
                </li>
                <%
                } else if (user1.getUserType().equals("admin")) {
                %>
                <li class="nav-item active">
                    <a class="nav-link" href="admin.jsp">Panel administratora</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="LogoutServlet">Wyloguj</a>
                </li>
                <%} else if (user1.getUserType().equals("normal")){%>
                <li class="nav-item active">
                    <a class="nav-link"> <%=user1.getUserEmail()%>
                    </a>
                </li>
                <li class="nav-item active">
                    <a href="userData.jsp" class="nav-link">Moje konto
                    </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="LogoutServlet">Wyloguj</a>
                </li>
                <%
                    }else{
                %>
                <li class="nav-item active">
                    <a class="nav-link" href="login.jsp">Zaloguj</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="register.jsp">Utwórz konto</a>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>