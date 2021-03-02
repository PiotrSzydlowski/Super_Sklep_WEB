<%@ page import="entity.Category" %>
<%@ page import="java.util.List" %>
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

    String title = (String) session.getAttribute("pTitle");
    String desc = (String) session.getAttribute("pDesc");
    Double price = (double) session.getAttribute("pPrice");
    Double discount = (double) session.getAttribute("pDiscount");
    int quantity = (int) session.getAttribute("pQuantity");
    List<Category> listAllCategory = (List<Category>) session.getAttribute("pCategory");

    int idProduct = (int) session.getAttribute("idProduct");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
    <title>Edytuj Produkt</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="container mt-5">
    <%@include file="components/message.jsp" %>
    <div class="row md-4">
        <div class="col-md-12 col-md-offset-8">
            <div class="well well-sm">
                <form class="form-horizontal" action="EditProductServlet" method="get">
                    <input type="hidden" name="operationGet" value="editProductOperation"/>
                    <fieldset>
                        <legend class="text-center">Edycja Produktu o id:  <%=idProduct%>
                        </legend>
                        <!-- Name input-->
                        <div class="form-group mt-3">
                            <input name="productName" type="text" value="<%=title%>" class="form-control">
                        </div>
                        <!-- Desc body -->
                        <div class="form-group mt-3">
                            <input name="productDesc" class="form-control" name="message" value="<%=desc%>" rows="5">
                        </div>
                        <div class="form-group mt-3">
                            <input name="productPrice" class="form-control" name="message" value="<%=price%>" rows="5">
                        </div>
                        <div class="form-group mt-3">
                            <input name="productDiscount" class="form-control" name="message" value="<%=discount%>"
                                   rows="5">
                        </div>
                        <div class="form-group mt-3">
                            <input name="productQuantity" class="form-control" name="message" value="<%=quantity%>"
                                   rows="5">
                        </div>
                        <div class="form-group mt-3">
                            <select name="catId" class="form-control" id="">
                                <%
                                    for (Category c : listAllCategory) {
                                %>
                                <option name="categoryList" value="<%=c.getCategoryId()%>"><%=c.getCategoryTitle()%>
                                </option>
                                <%}%>
                            </select>
                        </div>
                        <!-- Form actions -->
                        <div class="form-group">
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-success btn-lg">Edytuj</button>
                                <a href="productsTable.jsp">
                                    <button type="button" class="btn btn-warning btn-lg">Wróć</button>
                                </a>
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
