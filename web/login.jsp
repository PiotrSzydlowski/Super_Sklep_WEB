<%--
  Created by IntelliJ IDEA.
  User: piotr Szydłowski
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
        <%@include file="components/common_css_js.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Logowanie</title>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="login-form">
    <h2 class="text-center">Zaloguj</h2>
    <form action="LoginServlet" method="post">
        <%@include file="components/message.jsp"%>
        <%@include file="components/validateForm.jsp"%>
        <div class="avatar">
            <img src="img/person.png" alt="Avatar">
        </div>
        <div class="form-group">
            <input type="text" class="form-control input-lg" name="username" placeholder="Adres email" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control input-lg" name="password" placeholder="Hasło" required="required">
        </div>
        <div class="form-group clearfix">
            <button type="submit" class="btn btn-primary btn-lg float-right">Zaloguj</button>
        </div>
    </form>
    <div class="hint-text">Nie posiadasz konta? <a href="register.jsp">Zarejesruj się</a></div>
</div>
</body>
</html>

