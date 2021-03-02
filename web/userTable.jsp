<%@ page import="Dao.UserDao" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
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
            return;
        }
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<!-- Editable table -->
<div class="card">
    <div class="container-fluid mt-3">
        <%@include file="components/message.jsp" %>
        <%@include file="components/validateForm.jsp" %>
    </div>
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Użytkownicy</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
            <form style="max-width: 300px" action="" method="get">
                <input style="margin-bottom: 20px " class="form-control" name="search" type="text" placeholder="szukaj">
            </form>
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <tr>
                    <th class="text-center">Id Użytkownika</th>
                    <th class="text-center">Nazwa Użytkownika</th>
                    <th class="text-center">Numer telefonu</th>
                    <th class="text-center">Adres email</th>
                    <th class="text-center">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <%
                    UserDao userDao = new UserDao();
                    String search = request.getParameter("search");
                    List<User> allUsers;

                    if (search != null) {
                        allUsers = userDao.getUsersBySearching(search);
                    } else {
                        allUsers = userDao.getAllUsers();
                    }
                    for (User u : allUsers) {
                %>
                <tr>
                    <td class="pt-3-half" contenteditable="false"><%=u.getUserID()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=u.getUserName()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=u.getUserPhone()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=u.getUserEmail()%>
                    </td>
                    <td class="pt-3-half">
                        <div class="container">
                            <div class="row justify-content-center">
                                <form action="EditUserServlet" method="get">
                                    <div class="container mr-3">
                                        <button type="submit" name="editUserBtn" value="<%=u.getUserID()%>"
                                                class="btn btn-warning btn-rounded btn-sm my-0">
                                            <i style="font-size: 20px" class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        </button>
                                    </div>
                                </form>
                                <form action="UserServlet" method="post">
                                    <button type="submit" name="deleteUserBtn" value="<%=u.getUserID()%>"
                                            class="btn btn-danger btn-rounded btn-sm my-0">
                                        <i style="font-size: 20px" class="fa fa-trash" aria-hidden="true"></i>
                                    </button>
                                </form>
                                <form action="UserServlet" method="get">
                                    <button style="margin-left: 4px" type="submit" name="getHistory"
                                            value="<%=u.getUserID()%>"
                                            class="btn btn-warning btn-rounded btn-sm my-0">
                                        <i style="font-size: 20px" class="fa fa-history" aria-hidden="true"></i>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <div>
        <a href="admin.jsp">
            <button type="button" class="btn btn-info ml-5"><i  style="font-size: 20px" class="fa fa-undo" aria-hidden="true"></i> Wróć
            </button>
        </a>
    </div>
</div>
</body>
</html>
