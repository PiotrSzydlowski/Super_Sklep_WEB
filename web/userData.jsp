<%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="components/common_css_js.jsp"%>
    <%@include file="components/navbar.jsp" %>
    <%@include file="components/navbarAccount.jsp"%>
    <title>Dane</title>
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("current-user");
%>
<div style="margin: 30px 150px ">
<h1>Dane Użytkownika:</h1>
<h3>Nazwa: <%=currentUser.getUserName()%></h3>
<h3>Email: <%=currentUser.getUserEmail()%></h3>
<h3>Telefon: <%=currentUser.getUserPhone()%></h3>
<h3>Adres: <%=currentUser.getUserAddress()%></h3>
</div>
</body>
</html>
