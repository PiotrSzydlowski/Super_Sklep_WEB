<%@ page import="Dao.ProductDao" %>
<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Category" %>
<%@ page import="Dao.CategoryDao" %><%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Home</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>

<%--<div class="card-body" style="width: 100%; height: 160px; border: red 1px solid">--%>
<div style="width: 95%; height: 160px; margin: 15px auto" id="carouselExampleSlidesOnly" class="carousel slide "
     data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img style="max-width: 80%; height: 100px" src="img/nrd-D6Tu_L3chLE-unsplash.jpg" class="d-block w-100">
        </div>
        <div class="carousel-item">
            <img style="max-width: 80%; height: 100px" src="img/26970.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="..." class="d-block w-100" alt="...">
        </div>
    </div>
</div>
<%--</div>--%>

<div class="container-fluid">
    <div class="row mt-3 mx-2">

        <%
            String cat = request.getParameter("cat");
            ProductDao productDao = new ProductDao();
            List<Product> listAllProducts;
            if (cat == null || cat.equals("all") || cat.isEmpty()) {
//                listAllProducts = productDao.getAllProducts();
                listAllProducts = productDao.getAllProducts();
            } else {
                int categoryId = Integer.parseInt(cat);
                listAllProducts = productDao.getAllProductsById(categoryId);
            }
            CategoryDao categoryDao = new CategoryDao();
            List<Category> listAllCategory = categoryDao.getAllCategory();
        %>
        <%--//show category--%>
        <div class="col-md-2">
            <div class="list-group mt-4">
                <a href="index.jsp?cat=all" class="list-group-item list-group-item-action active">Wszystkie
                    produkty</a>
            </div>
            <%
                for (Category category : listAllCategory) {
            %>
            <a href="index.jsp?cat=<%=category.getCategoryId()%>"
               class="list-group-item list-group-item-action"><%=category.getCategoryTitle()%>
                <%
                    long productFromCategory = productDao.getProductFromCategory(category.getCategoryId());
                %>
                <span style="padding-left: 8px">(<%=productFromCategory%>)</span>
            </a>
            <% } %>
        </div>
        <%--//show product--%>
        <div class="col-md-10">
            <%--       wiersze--%>
            <div class="row mt-4">
                <%--            kolumny--%>
                <div class="col-md-12">
                    <div class="card-columns">
                        <%--                    traversing product--%>
                        <%
                            for (Product product : listAllProducts) {
                        %>
                        <div class="card product-card">
                            <div class="container text-center">
                                <img src="img/products/<%=product.getProductphoto()%>"
                                     style="max-height: 200px;max-width: 100%;width: auto" class="card-img-top">
                            </div>
                            <div class="card-body">
                                <h5 class="card-title"><%=product.getProducttitle().toUpperCase()%>
                                </h5>
                                <p class="card-text">
                                    <%=product.getProductdescrption()%>
                                </p>
                                <div class="footer">
                                    <button class="btn custom-bg text-white"
                                            onclick="add_to_cart(<%=product.getProductid()%>, '<%=product.getProducttitle()%>',
                                                <%=product.getPriceAfterDiscount()%>)">Dodaj do koszyka
                                    </button>
                                    <%
                                        if (product.getProductdiscount() > 0) {
                                    %>
                                    <button class="btn btn-outline-primary"><%=product.getPriceAfterDiscount() + " zł - "%>
                                        <span class="text-secondary discount-label">
                                            <%=product.getProductprice() + " zł"%>,
                                        <%=product.getProductdiscount() + "% obniżki"%>
                                    </span></button>
                                    <%} else {%>
                                    <button class="btn btn-outline-primary"><%=product.getProductprice() + " zł"%>
                                    </button>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                            if (listAllProducts.size() == 0) {
                                out.println("<h3>Brak produktów dla Kategorii<h3>");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="components/common_modals.jsp" %>
</body>
</html>
