<%@ page import="entity.Category" %>
<%@ page import="Dao.CategoryDao" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Raport Kategorie</title>
</head>
<body>
<table>
    <tr>
        <td class="text-center">ID</td>
        <td class="text-center">NAZWA KATEGORII</td>
        <td class="text-center">OPIS KATEGORII</td>
    </tr>
    <%
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=raportKategorii.xls");
        List<Category> listAllCategory = (List<Category>) session.getAttribute("categoryReport");
        for (Category c : listAllCategory) {
    %>
    <tr>
        <td class="text-center"><%=c.getCategoryId()%>
        </td>
        <td class="text-center"><%=c.getCategoryTitle()%>
        </td>
        <td class="text-center"><%=c.getCategoryDescription()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
