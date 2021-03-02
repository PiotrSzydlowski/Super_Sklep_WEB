<%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
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
<%@include file="components/common_css_js.jsp"%>
<%@include file="components/navbar.jsp" %>
<%@include file="components/navbarAccount.jsp"%>
<div class="login-form">
    <h2 class="text-center">Zmień hasło</h2>
        <div class="hint-text">* HASŁO MUSI MIEĆ MIN.8 ZNAKÓW, WIELKĄ LITERTĘ I ZNAK SPECJALNY</div>
    <form action="ChangePasswordServlet" method="post">
        <%@include file="components/message.jsp"%>
        <%@include file="components/validateForm.jsp"%>
        <div class="form-group">
            <input type="password" class="form-control input-lg" name="currentPass" placeholder="Obecne hasło" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control input-lg" name="newPass" placeholder="Nowe hasło" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control input-lg" name="repeatNewPass" placeholder="Potwórz hasło" required="required">
        </div>
        <div class="form-group clearfix">
            <button type="submit" class="btn btn-primary btn-lg float-right">Zatwierdź</button>
        </div>
    </form>
</div>
</body>
</html>

