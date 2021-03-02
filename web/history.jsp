<%@ page import="Dao.OrderDao" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Order" %>
<%@ page import="Dao.ProductDao" %>
<%@ page import="entity.Product" %>
<%@ page import="Dao.UserDao" %><%--
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
    <title>Historia zamówień</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<%
    int getHistoryByIdUser = (int) session.getAttribute("getHistoryByUserId");
    OrderDao orderDao = new OrderDao();
    List<Order> listOrdersById = orderDao.getOrdersById(getHistoryByIdUser);
%>
<!-- Editable table -->
<div class="card">
    <div class="container-fluid mt-3">
        <%@include file="components/message.jsp" %>
        <%@include file="components/validateForm.jsp" %>
    </div>
    <%
        UserDao userDao = new UserDao();
        User userById = userDao.getUserById(getHistoryByIdUser);
    %>
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Historia
        zamówień: <%=userById.getUserEmail()%>
    </h3>
    <div class="card-body">
        <div id="table" class="table-editable">
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <tr>
                    <th class="text-center">Nazwa produktu</th>
                    <th class="text-center">Suma zamówienia</th>
                    <th class="text-center">Status zamówienia</th>
                    <th class="text-center">Numer zamówienia</th>
                    <th class="text-center">Ilość</th>
                    <th class="text-center">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Order o : listOrdersById) {
                %>
                <tr>
                    <td class="pt-3-half" contenteditable="false">
                        <%
                            ProductDao productDao = new ProductDao();
                            Product productById = productDao.getProductById(o.getProductid());
                        %>
                        <%=productById.getProducttitle()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=o.getTotalprice()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=o.getOrderstatus()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=o.getOrdernumber()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=o.getQuantity()%>
                    </td>
                    <td class="pt-3-half">
                        <div class="container">
                            <div class="row justify-content-center">
                                <form action="HistoryServlet" method="post">
                                    <div class="container mr-3">
                                        <button type="submit" name="cancelOrder" value="<%=o.getOrdernumber()%>"
                                                class="btn btn-danger btn-rounded btn-sm my-0">
                                            <i style="font-size: 20px" class="fa fa-trash" aria-hidden="true"></i>
                                        </button>
                                    </div>
                                </form>
                                <%--                                <form action="HistoryServlet" method="get">--%>
                                <a href="invoiceReport.jsp">
                                    <button type="submit" name="printInvoice" value="<%=o.getOrdernumber()%>"
                                            class="btn btn-success btn-rounded btn-sm my-0">Drukuj
                                    </button>
                                </a>
                                <%--                                </form>--%>
                            </div>
                        </div>
                    </td>
                </tr>
                <%
                        session.setAttribute("productById", productById);
                        session.setAttribute("orderNumber", o.getOrdernumber());
                    }%>
                </tbody>
            </table>
        </div>
    </div>
    <div>
        <a href="userTable.jsp">
            <button type="button" class="btn btn-info ml-5"><i style="font-size: 20px" class="fa fa-undo"
                                                               aria-hidden="true"></i> Wróć
            </button>
        </a>
    </div>
</div>
</body>
</html>
