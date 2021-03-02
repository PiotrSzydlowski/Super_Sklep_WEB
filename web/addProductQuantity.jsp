<%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("current-user");
    if (user == null) {
        session.setAttribute("message", "Nie zostałeś zalogowany");
        response.sendRedirect("login.jsp");
        return;
    } else {
        if (user.getUserType().equals("normal")) {
            session.setAttribute("message", "Nie masz dostępu do tej strony");
            response.sendRedirect("index.jsp");
        }
    }
    String productIdQuantity = (String) session.getAttribute("productIdQuantity");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
    <title>Dodaj Ilość</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="container mt-5">
    <%@include file="components/message.jsp"%>
    <div class="row md-4">
        <div class="col-md-12 col-md-offset-8">
            <div class="well well-sm">
                <form class="form-horizontal" action="EditProductServlet" method="get">
                    <input type="hidden" name="operationGet" value="addProductQuantityOperation"/>
                    <fieldset>
                        <legend class="text-center">Dodanie ilości produktu o id: <%=productIdQuantity%></legend>
                        <!-- Name input-->
                        <div class="form-group mt-3">
                            <div class="col-md-12 border">
                                <span> Ilość</span> <input  name="quantity" type="text" value="" class="form-control">
                            </div>
                        </div>
                        <!-- Form actions -->
                        <div class="form-group">
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-success btn-lg">Dodaj</button>
                                <a href="productsTable.jsp"><button type="button" class="btn btn-warning btn-lg">Wróć</button></a>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>