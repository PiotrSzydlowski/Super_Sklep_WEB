<%@ page import="servlets.CheckoutServlet" %><%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>

<%
    User user = (User) session.getAttribute("current-user");
    if (user == null) {
        session.setAttribute("message", "Nie jesteś zalogowany. Zaloguj się żeby sfinalizować transakcję");
        response.sendRedirect("login.jsp");
        return;
    }

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="components/common_css_js.jsp" %>
>
<%@include file="components/common_modals.jsp" %>
<%@include file="components/navbar.jsp" %>
<head>
    <title>Zamówienie</title>
</head>
<body>
<div class="container">
    <div class="row mt-5">
        <div class="col-md-6">
            <%--cart detail--%>
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center mb-5">Twoje wybrane produkty</h3>
                    <script>
                        let item = localStorage.getItem(updateCart());

                    </script>
                    <div class="cart-body"></div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <%--           form detail--%>
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center mb-5">Dane do zamówienia</h3>
                    <form action="CheckoutServlet" method="post">
                        <input type="hidden" name="checkout" value="<%=user.getUserID()%>">
                        <div class="form-group">
                            <label for="inputEmail1">Adres Email</label>
                            <input name="user_email" value="<%=user.getUserEmail()%>" type="email" class="form-control"
                                   id="inputEmail1" aria-describedby="emailHelp" placeholder="Wprowadź e-mail">
                        </div>
                        <div class="form-group">
                            <label>Twoje imię</label>
                            <input name="user_name" value="<%=user.getUserName()%>" type="text" class="form-control"
                                   id="name" aria-describedby="name" placeholder="Wprowadź Imię">
                        </div>
                        <div class="form-group">
                            <label>Telefon</label>
                            <input name="user_phone" value="<%=user.getUserPhone()%>" type="text" class="form-control"
                                   id="phone" aria-describedby="name" placeholder="Wprowadź Numer Telefonu">
                        </div>
                        <div class="form-group">
                            <label>Adres dostawy</label>
                            <input name="user_address" value="<%=user.getUserAddress()%>" class="form-control"
                                   id="shippingAddress" placeholder="Wprowadź adres dostawy" rows="3">
                        </div>
                        <div class="container text-center">
                            <button id="goOrder" class="btn btn-outline-success">Realizuj zamówienie</button>
                            <button class="btn btn-outline-primary"><a href="index.jsp">Kontynuj Zakupy</a></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
