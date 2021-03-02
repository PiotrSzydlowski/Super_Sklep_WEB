<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Raport Produktu</title>
</head>
<body>
<table>
    <tr>
        <td class="text-center">ID</td>
        <td class="text-center">NAZWA PRODUKTU</td>
        <td class="text-center">OPIS PRODUKTU</td>
        <td class="text-center">CENA PRODUKTU</td>
        <td class="text-center">OBNIZKA CENY</td>
        <td class="text-center">ILOSC PRODUKTU</td>
    </tr>
    <%
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=raportProduktów.xls");
        List<Product> listAllProduct = (List<Product>) session.getAttribute("productReport");
        for (Product p : listAllProduct) {
    %>
    <tr>
        <td class="text-center"><%=p.getProductid()%>
        </td>
        <td class="text-center"><%=p.getProducttitle()%>
        </td>
        <td class="text-center"><%=p.getProductdescrption()%>
        </td>
        <td class="text-center"><%=p.getProductprice()%>
        </td>
        <td class="text-center"><%=p.getProductdiscount()%>
        </td>
        <td class="text-center"><%=p.getProductquantity()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>

