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
    int userIdEdit = (int) session.getAttribute("userIdEdit");
    String userEditName = (String) session.getAttribute("userEditName");
    String userEditEmail = (String) session.getAttribute("userEditEmail");
    String userEditPhone = (String) session.getAttribute("userEditPhone");
    String userEditPass = (String) session.getAttribute("userEditPass");
    String userEditAddress = (String) session.getAttribute("userEditAddress");
    String userEditType = (String) session.getAttribute("userEditType");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
    <title>Edycja użytkownika</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="container mt-5">
    <%@include file="components/message.jsp"%>
    <div class="row md-4">
        <div class="col-md-12 col-md-offset-8">
            <div class="well well-sm">
                <form class="form-horizontal" action="EditUserServlet" method="post">
                    <fieldset>
                        <legend class="text-center">Edycja użytkownika o id: <%=userIdEdit%> </legend>
                        <!-- Name input-->
                        <div style="max-width: 500px; margin-left: 350px" class="form-group mt-3">
                                <input  name="userName" type="text" value="<%=userEditName%>" class="form-control">
                        </div>
                        <!-- Desc body -->
                        <div style="max-width: 500px; margin-left: 350px" class="form-group mt-3">
                                <input name="userEmail" class="form-control"  name="message" value="<%=userEditEmail%>" rows="5">
                        </div>
                        <div style="max-width: 500px; margin-left: 350px" class="form-group mt-3">
                            <input  name="userPhone" type="text" value="<%=userEditPhone%>" class="form-control">
                        </div>
                        <div style="max-width: 500px; margin-left: 350px" class="form-group mt-3">
                            <input  name="userPass" type="text" value="<%=userEditPass%>" class="form-control">
                        </div>
                        <div style="max-width: 500px; margin-left: 350px" class="form-group mt-3">
                            <input  name="userAddress" type="text" value="<%=userEditAddress%>" class="form-control">
                        </div>
                        <div style="max-width: 500px; margin-left: 350px" class="form-group mt-3">
                            <input  name="userType" type="text" value="<%=userEditType%>" class="form-control">
                        </div>
                        <!-- Form actions -->
                        <div class="form-group">
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-success btn-lg">Edytuj</button>
                                <a href="userTable.jsp"><button type="button" class="btn btn-warning btn-lg">Wróć</button></a>
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



