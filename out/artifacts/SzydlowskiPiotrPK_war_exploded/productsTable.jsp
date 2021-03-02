<%@ page import="java.util.List" %>
<%@ page import="Dao.ProductDao" %>
<%@ page import="entity.Product" %>
<%@ page import="Dao.AvabilityDao" %>
<%@ page import="entity.Avability" %>
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
    <title>Produkty</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>

<%@include file="components/navbar.jsp" %>
<%
    AvabilityDao avabilityDao = new AvabilityDao();
    List<Avability> allAvability = avabilityDao.getAllAvability();
%>
<!-- Editable table -->
<div class="card">
    <div class="container-fluid mt-3">
        <%@include file="components/message.jsp" %>
        <%@include file="components/validateForm.jsp" %>
    </div>

    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Produkty</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
            <form style="max-width: 300px" action="" method="get">
                <input style="margin-bottom: 20px " class="form-control" name="search" type="text" placeholder="szukaj">
                <a href="admin.jsp">
                    <button name="" type="button" class="btn btn-info ml-5"><i style="font-size: 20px"
                                                                               class="fa fa-undo"
                                                                               aria-hidden="true"></i>
                        Wróć
                    </button>
                </a>
            </form>
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <tr>
                    <th class="text-center">Id</th>
                    <th class="text-center">Nazwa</th>
                    <th class="text-center">Opis</th>
                    <th class="text-center">Cena</th>
                    <th class="text-center">Obniżka</th>
                    <th class="text-center">Ilość</th>
                    <th class="text-center">Kategoria</th>
                    <th class="text-center">Dostępność</th>
                    <th class="text-center" style="min-width: 250px">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ProductDao productDao = new ProductDao();
                    String search = request.getParameter("search");
                    List<Product> listAllProducts;
                    if (search != null) {
                        listAllProducts = productDao.getProductBySearch(search);
                    } else {
                        listAllProducts = productDao.getAllProducts();
                    }
                    for (Product p : listAllProducts) {
                %>
                <tr>
                    <td class="pt-3-half" contenteditable="false"><%=p.getProductid()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=p.getProducttitle()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=p.getProductdescrption()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=p.getProductprice()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=p.getProductdiscount()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=p.getProductquantity()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false"><%=p.getCategory()%>
                    </td>
                    <td class="pt-3-half" contenteditable="false">
                        <%
                            int available = productDao.getQuantity(p.getProductid());
                            Avability avabilityHight = avabilityDao.getAvability(3);
                            int hight = avabilityHight.getAvabilityvalue();
                            Avability avabilityMiddle = avabilityDao.getAvability(2);
                            int middle = avabilityMiddle.getAvabilityvalue();

                            if (available >= hight) {
                        %>
                        <div class="progress">
                            <div class="progress-bar bg-success" role="progressbar" style="width: 100%"
                                 aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                        <%} else if (available >= middle) {%>
                        <div class="progress">
                            <div class="progress-bar bg-warning" role="progressbar" style="width: 50%"
                                 aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                        <%} else if (available < middle) {%>
                        <div class="progress">
                            <div class="progress-bar bg-danger" role="progressbar" style="width: 15%" aria-valuenow="15"
                                 aria-valuemin="0" aria-valuemax="100"></div>
                        </div
                        <%}%>
                    </td>
                    <td class="pt-3-half">
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="btn-group">
                                    <form action="EditProductServlet" method="post">
                                        <input type="hidden" name="operationPost" value="editProductOperation"/>
                                        <div class="container mr-3">
                                            <button data-toggle="tooltip" data-placement="top"
                                                    title="Edytuj rekord" type="submit" name="editProductBtn"
                                                    value="<%=p.getProductid()%>"
                                                    class="btn btn-warning btn-rounded btn-sm my-0">
                                                <i style="font-size: 20px" class="fa fa-pencil-square-o"
                                                   aria-hidden="true"></i>
                                            </button>
                                        </div>
                                    </form>
                                    <%--                                    //przycisk dodaj ilosc --%>
                                    <form action="EditProductServlet" method="post">
                                        <input type="hidden" name="operationPost" value="addQuantity"/>
                                        <div class="container mr-3">
                                            <button data-toggle="tooltip" data-placement="top"
                                                    title="Dodaj Dostepność" type="submit" name="addQuantityBtn"
                                                    value="<%=p.getProductid()%>"
                                                    class="btn btn-warning btn-rounded btn-sm my-0">
                                                <i style="font-size: 20px" class="fa fa-plus" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                    </form>
                                    <%--                                    przycisk usun--%>
                                    <form action="ProductServlet" method="post">
                                        <button data-toggle="tooltip" data-placement="top"
                                                title="Usuń" type="submit" name="deleteProductBtn"
                                                value="<%=p.getProductid()%>"
                                                class="btn btn-danger btn-rounded btn-sm my-0">
                                            <i style="font-size: 20px" class="fa fa-trash" aria-hidden="true"></i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                <%
                    }
                    session.setAttribute("productReport", listAllProducts);
                %>
                </tbody>
            </table>
        </div>
    </div>
    <div>
        <a href="productReport.jsp">
            <button data-toggle="tooltip" data-placement="top" title="Eksport do Excel" type="button"
                    class="btn btn-info ml-5"><i style="font-size: 20px" class="fa fa-print" aria-hidden="true"></i>
            </button>
        </a>
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
            Ustaw Dostępność
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a data-toggle="modal" data-target="#add-avability-hight" class="dropdown-item" href="#">Wysoka</a>
            <a data-toggle="modal" data-target="#add-avability-middle" class="dropdown-item" href="#">Srednia</a>
            <%--                <a data-toggle="modal" data-target="#add-avability-low" class="dropdown-item" href="#">Mała</a>--%>
        </div>
        <a href="admin.jsp">
            <button type="button" class="btn btn-info ml-5"><i style="font-size: 20px" class="fa fa-undo"
                                                               aria-hidden="true"></i>
                Wróć
            </button>
        </a>
    </div>
    <%--    modal Hight avability--%>
    <div class="modal fade" id="add-avability-hight" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header custom-bg text-white">
                    <h5 class="modal-title" id="exampleModalLabel">Ustaw Dostępność</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"></span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="AvabilityServlet" method="post">
                        <input type="hidden" name="avabilityOperation" value="avability-hight">
                        <table class="table table-bordered table-responsive-md table-striped text-center">
                            <thead>
                            <tr>
                                <th contenteditable="false" class="text-center">Nazwa</th>
                                <th contenteditable="false" class="text-center">Id</th>
                                <th class="text-center">Wartość</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="pt-3-half" contenteditable="false">Wysoka</td>
                                <td class="pt-3-half" contenteditable="false"><input name="idHight" value="3"
                                                                                     class="form-control" rows="5"></td>
                                <td class="pt-3-half" contenteditable="true"><input name="valueHight"
                                                                                    class="form-control" rows="5"></td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">Ustaw dostępność</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Wyjście</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%--    modal Middle avability--%>
    <div class="modal fade" id="add-avability-middle" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header custom-bg text-white">
                    <h5 class="modal-title" id="exampleModalLabelMidlle">Ustaw Dostępność</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"></span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="AvabilityServlet" method="post">
                        <input type="hidden" name="avabilityOperation" value="avability-middle">
                        <table class="table table-bordered table-responsive-md table-striped text-center">
                            <thead>
                            <tr>
                                <th class="text-center">Nazwa</th>
                                <th class="text-center">Id</th>
                                <th class="text-center">Wartość</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="pt-3-half" contenteditable="false">Srednia</td>
                                <td class="pt-3-half"><input contenteditable="false" name="idMiddle" value="2"
                                                             class="form-control" rows="5"></td>
                                <td class="pt-3-half" contenteditable="true"><input name="valueMiddle"
                                                                                    class="form-control" rows="5"></td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">Ustaw dostępność</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Wyjście</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
