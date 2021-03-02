<%@ page import="entity.User" %><%--
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
    String title = (String) session.getAttribute("title");
    String desc = (String) session.getAttribute("desc");
    int idCategory = (int) session.getAttribute("idCategory");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
    <title>Edytuj Kategorię</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="container mt-5">
    <%@include file="components/message.jsp" %>
    <div class="row md-4">
        <div class="col-md-12 col-md-offset-8">
            <div class="well well-sm">
                <form class="form-horizontal" action="EditCategoryServlet" method="post">
                    <fieldset>
                        <%--                        Przekazywanie id kategorii po naciśnięciu przycisku edytuj kategorię--%>
                        <legend class="text-center">Edycja Kategorii o id:  <%=idCategory%>
                        </legend>
                        <!-- Name input-->
                        <div class="form-group mt-3">
                            <%--                            przekazywanie w value tytuły kategorii--%>
                            <input name="categoryName" type="text" value="<%=title%>" class="form-control">
                        </div>
                        <!-- Desc body -->
                        <div class="form-group mt-3">
                            <%--                            przekazywanie w value opisu kategorii--%>
                            <input name="categoryDesc" class="form-control" name="message" value="<%=desc%>" rows="5">
                        </div>
                        <!-- Form actions -->
                        <div class="form-group">
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-success btn-lg">Edytuj</button>
                                <a href="categoryTable.jsp">
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


