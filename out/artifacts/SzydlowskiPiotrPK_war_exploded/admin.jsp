<%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>

<%@ page import="Dao.CategoryDao" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.UserDao" %>
<%@ page import="Dao.ProductDao" %>
<%@ page import="Dao.SaleDao" %>
<%@ page import="entity.*" %>
<%
    User user = (User) session.getAttribute("current-user");
    if (user == null) {
        session.setAttribute("message", "Nie zostałeś zalogowany");
        response.sendRedirect("login.jsp");
        return;
    } else {
        if (user.getUserType().equals("normal")) {
            session.setAttribute("message", "Nie masz dostępu do tej strony");
            response.sendRedirect("login.jsp");
        }
    }
%>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@include file="components/common_css_js.jsp" %>
    <title>Admin Panel</title>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="container admin">
    <%
        ProductDao productDao = new ProductDao();
        List<Product> listAllProducts = productDao.getAllProducts();
        UserDao userDao = new UserDao();
        List<User> listAllUsers = userDao.getAllUsers();
        CategoryDao categoryDao = new CategoryDao();
        List<Category> listAllCategory = categoryDao.getAllCategory();
        SaleDao saleDao = new SaleDao();
        List<Sale> allSale = saleDao.getAllSale();

    %>
    <div class="container-fluid mt-3">
        <%@include file="components/message.jsp" %>
        <%@include file="components/validateForm.jsp" %>
    </div>
    <div class="row mt-3">
        <div class="col-md-4">
            <div class="card">
                <a href="userTable.jsp">
                    <div class="card-body text-center">
                        <div class="container">
                            <img style="max-width: 160px" class="img-fluid rounded-circle"
                                 src="img/icons8-user-group-96.png">
                        </div>
                        <h3><%=listAllUsers.size()%>
                        </h3>
                        <h2 class="text-uppercase text-muted">Użytkownicy</h2>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <a href="promotion.jsp">
                    <div class="card-body text-center">
                        <div class="container">
                            <img style="max-width: 160px; min-height: 96px" class="img-fluid rounded-circle"
                                 src="img/sale.png">
                        </div>
                        <h3><%=allSale.size()%>
                        </h3>
                        <h2 class="text-uppercase text-muted">Promocje</h2>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <a href="categoryTable.jsp">
                    <div class="card-body text-center">
                        <div class="container">
                            <img style="max-width: 160px" class="img-fluid rounded-circle" src="img/icons8-list-96.png">
                        </div>
                        <h3><%=listAllCategory.size()%>
                        </h3>
                        <h2 class="text-uppercase text-muted">Kategorie</h2>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <a href="productsTable.jsp">
                    <div class="card-body text-center">
                        <div class="container">
                            <img style="max-width: 160px" class="img-fluid rounded-circle"
                                 src="img/icons8-open-box-96.png">
                        </div>
                        <h3><%=listAllProducts.size()%>
                        </h3>
                        <h2 class="text-uppercase text-muted">Produkty</h2>
                    </div>
                </a>
            </div>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col-md-6">
            <div class="card" data-toggle="modal" data-target="#add-category-modal">
                <div class="card-body text-center">
                    <div class="container">
                        <img style="max-width: 160px" class="img-fluid rounded-circle" src="img/icons8-add-node-96.png">
                    </div>
                    <p class="mt-2 text-muted">kliknij aby dodać kategorię</p>
                    <h2 class="text-uppercase text-muted">Dodaj Kategorię</h2>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card" data-toggle="modal" data-target="#add-promotion-modal">
                <div class="card-body text-center">
                    <div class="container">
                        <img style="max-width: 160px; min-height: 95px" class="img-fluid rounded-circle"
                             src="img/discount.png">
                    </div>
                    <p class="mt-2 text-muted">kliknij aby dodać promocję</p>
                    <h2 class="text-uppercase text-muted">Dodaj Promocję</h2>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card" data-toggle="modal" data-target="#add-product-modal">
                <div class="card-body text-center">
                    <div class="container">
                        <img style="max-width: 160px" class="img-fluid rounded-circle"
                             src="img/icons8-add-property-96.png">
                    </div>
                    <p class="mt-2 text-muted">kliknij aby dodać produkt</p>
                    <h2 class="text-uppercase text-muted">Dodaj Produkt</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="add-category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white">
                <h5 class="modal-title" id="exampleModalLabel">Dodaj kategorię</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="ProductOperationServlet" method="post">
                    <input type="hidden" name="operation" value="addcategory">
                    <div class="form-group">
                        <input type="text" class="form-control" name="catTitle" placeholder="Nazwa kategorii" required/>
                    </div>
                    <div class="form-group">
                        <textarea style="height: 250px" class="form-control" placeholder="Wprowadź opis kategorii"
                                  name="catDescription" required></textarea>
                    </div>
                    <div class="container text-center">
                        <button class="btn btn-outline-success">Dodaj Kategorię</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Wyjście</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add-promotion-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header custom-bg text-white">
                <h5 class="modal-title" id="exampleModalLabel2">Dodaj Promocję</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="PromotionServlet" method="post">
                    <input type="hidden" name="operation" value="addcategory">
                    <div class="form-group">
                        <input type="text" class="form-control" name="promotionCode" placeholder="Kod Promocji"
                               required/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="value" placeholder="Wartość" required/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="minPrice"
                               placeholder="Minimalna wartośc zamówienia" required/>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <input name="startDay" type="text" class="form-control" placeholder="Dzień rozpoczęcia"
                                   required>
                        </div>
                        <div class="col">
                            <input name="startMoth" type="text" class="form-control" placeholder="Miesiąc rozpoczęcia"
                                   required>
                        </div>
                        <div class="col">
                            <input name="startYear" type="text" class="form-control" placeholder="Rok rozpoczęcia"
                                   required>
                        </div>
                    </div>
                    <div style="margin-bottom: 20px" class="form-row">
                        <div style="margin-top: 15px" class="col">
                            <input name="endDay" type="text" class="form-control" placeholder="Dzień zakończenia"
                                   required>
                        </div>
                        <div style="margin-top: 15px" class="col">
                            <input name="endMoth" type="text" class="form-control" placeholder="Miesiąc zakończenia"
                                   required>
                        </div>
                        <div style="margin-top: 15px" class="col">
                            <input name="endYear" type="text" class="form-control" placeholder="Rok zakończenia"
                                   required>
                        </div>
                    </div>
                    <div class="container text-center">
                        <button class="btn btn-outline-success">Dodaj Promocję</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Wyjście</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%--Koniec okno modalne dodanie kategorii--%>
<!-- Modal -->
<div class="modal fade" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel1">Dodaj Produkt</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="operation" value="addproduct"/>
                    <%--                    nazwa produktu--%>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Wprowadż nazwę produktu" name="pName"
                               required/>
                    </div>
                    <%--                    opis produktu--%>
                    <div class="form-group">
                        <textarea style="height: 150px" class="form-control" placeholder="Wprowadź opis produktu"
                                  name="pDesc"></textarea>
                    </div>
                    <%--                    cena produktu--%>
                    <input type="text" class="form-control" placeholder="Wprowadż cenę produktu" name="pPrice"
                           required/>
                    <%--                obniżka ceny produktu--%>
                    <input type="number" class="form-control" placeholder="Wprowadż obniżkę ceny produktu"
                           name="pDiscount"
                           required/>
                    <%--            ilość produktu--%>
                    <input type="number" class="form-control" placeholder="Wprowadż ilość produktu" name="pQuantity"
                           required/>
                    <%--                    kategoria produktu--%>

                    <div class="form-group">
                        <select name="catId" class="form-control" id="">
                            <%
                                for (Category c : listAllCategory) {
                            %>
                            <option value="<%=c.getCategoryId()%>"><%=c.getCategoryTitle()%>
                            </option>
                            <%}%>
                        </select>
                    </div>
                    <%--                    produkt file--%>
                    <div class="form-group">
                        <label for="pPic">Wybierz zdjęcie produktu</label>
                        <br>
                        <input type="file" id="pPic" name="pPic"/>
                    </div>
                    <div class="container text-center">
                        <button class="btn btn-outline-success">Dodaj Produkt</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Wyjdź</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="components/common_modals.jsp" %>
</body>
</html>
