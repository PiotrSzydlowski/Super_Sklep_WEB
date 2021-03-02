<%@ page import="java.util.Calendar" %>
<%@ page import="Dao.OrderDao" %>
<%@ page import="entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %>
<%@ page import="Dao.ProductDao" %><%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Zamówienie</title>
</head>
<body>
<%
    int orderNumber = (int) session.getAttribute("orderNumber");
    int year = Calendar.getInstance().get(Calendar.YEAR);
    OrderDao orderDao = new OrderDao();
    List<Order> orderByNumberList = orderDao.getOrderByNumber(orderNumber);
%>
<table>
    <td>Zamowienie nr: <%=orderNumber%>/<%=year%></td>
</table>
<table>
    <tr>
        <td class="text-center">ID</td>
        <td class="text-center">NAZWA PRODUKTU</td>
        <td class="text-center">CENA PRODUKTU</td>
        <td class="text-center">ILOSC PRODUKTU</td>
    </tr>
    <%

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=order.xls");
        for (Order o : orderByNumberList){
    %>
    <tr>
        <td class="text-center"><%=o.getProductid()%>
        </td>
        <td class="text-center">
            <%
                int productid = o.getProductid();
                ProductDao productDao = new ProductDao();
                Product productById = productDao.getProductById(productid);
            %>
            <%=productById.getProducttitle()%>
        </td>
        <td class="text-center"><%=o.getTotalprice()%>
        </td>
        <td class="text-center"><%=o.getQuantity()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>


