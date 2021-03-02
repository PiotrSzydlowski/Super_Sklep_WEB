<%@ page import="Dao.CategoryDao" %>
<%@ page import="entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.SaleDao" %>
<%@ page import="entity.Sale" %>
<%--
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
    <title>Procoje</title>
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
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Promocje</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <tr>
                    <th class="text-center">Id Promocji</th>
                    <th class="text-center">Kod Promocji</th>
                    <th class="text-center">Data Rozpoczęcia</th>
                    <th class="text-center">Data zakończenia</th>
                    <th class="text-center">Wartość</th>
                    <th class="text-center">Minimalna wartość zamówienia</th>
                    <th class="text-center">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <%
                    SaleDao saleDao = new SaleDao();
                    List<Sale> allSale = saleDao.getAllSale();
                    for (Sale s : allSale) {
                %>
                <tr>
                    <td class="pt-3-half" contenteditable="false"><%=s.getIdsale()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=s.getCode()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=s.getDatafrom()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=s.getDatato()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=s.getValue()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=s.getPrice()%>
                    </td>
                    <td class="pt-3-half">
                        <div class="container">
                            <div class="row justify-content-center">
                                <form action="DeletePromotionServlet" method="post">
                                    <button type="submit" name="deletePromotion" value="<%=s.getIdsale()%>"
                                            class="btn btn-danger btn-rounded btn-sm my-0">
                                        <i style="font-size: 20px" class="fa fa-trash" aria-hidden="true"></i>
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
            <button type="button" class="btn btn-info ml-5">
                <i style="font-size: 20px" class="fa fa-undo" aria-hidden="true"></i> Wróć
            </button>
        </a>
    </div>
</div>
</body>
</html>
