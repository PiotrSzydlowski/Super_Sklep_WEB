<%@ page import="Dao.CategoryDao" %>
<%@ page import="entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Order" %>
<%@ page import="Dao.ProductDao" %>
<%@ page import="Dao.OrderDao" %>
<%@ page import="entity.Product" %>
<%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zamówienia w realizacji</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/common_css_js.jsp"%>
<%@include file="components/navbar.jsp" %>
<%@include file="components/navbarAccount.jsp"%>
<!-- Editable table -->
<div class="card">
    <div class="container-fluid mt-3">
        <%@include file="components/message.jsp" %>
        <%@include file="components/validateForm.jsp" %>
    </div>
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Zamówienia w realizacji</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <tr>
                    <th class="text-center">Produkt</th>
                    <th class="text-center">Suma</th>
                    <th class="text-center">Status zamówienia</th>
                    <th class="text-center">ilość</th>
                    <th class="text-center">Numer zamówienia</th>
                    <th class="text-center">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <%
                    User user = (User) session.getAttribute("current-user");
                    int userID = user.getUserID();
                    OrderDao orderDao = new OrderDao();
                    List<Order> ordersById = orderDao.getOrdersByIdAndStatus(userID);
                    ProductDao productDao = new ProductDao();
                    for (Order o : ordersById) {
                %>
                <tr>
                    <%
                        Product productById = productDao.getProductById(o.getProductid());
                    %>
                    <td class="pt-3-half" contenteditable="false"><%=productById.getProducttitle()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=o.getTotalprice()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=o.getOrderstatus()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=o.getQuantity()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=o.getOrdernumber()%>
                    </td>
                    <td class="pt-3-half">
                        <div class="container">
                            <div class="row justify-content-center">
                                <form action="OrderToContinueServlet" method="get">
                                    <div class="container mr-3">
                                        <button type="submit" name="orderNumber" value="<%=o.getOrdernumber()%>"
                                                class="btn btn-warning btn-rounded btn-sm my-0">
                                            opłać zamówienie
                                        </button>
                                    </div>
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
        <a href="userData.jsp">
            <button type="button" class="btn btn-info ml-5">
                <i style="font-size: 20px" class="fa fa-undo" aria-hidden="true"></i> Wróć
            </button>
        </a>
    </div>
</div>
</body>
</html>