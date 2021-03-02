<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@include file="components/navbar.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="components/common_css_js.jsp" %>
<%
    int orderNumber = (int) session.getAttribute("orderNumber");
    int year = Calendar.getInstance().get(Calendar.YEAR);
    double totalPrice = (double) session.getAttribute("totalPrice");
%>
<html lang="pl">
<head>
    <title>Płatność</title>
    <%@include file="components/message.jsp" %>
</head>
<body>
<div class="container">
    <div class="row mt-3">
        <div class="col-md-8">
            <div class="padding">
                <div class="row">
                    <div class="col-sm-15">
                        <div class="card">
                            <div class="card-header">
                                <strong>Karta Kredytowa</strong>
                                <small>Wprowadź dane</small>
                            </div>
                            <div class="card-header">
                                <strong>Zamówienie nr: <%=orderNumber%>/<%=year%>
                                </strong>
                            </div>
                            <div class="card-header">
                                <strong>Kwota zamówienia: <%=totalPrice%> zł </strong>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Numer karty kredytowej</label>
                                            <div class="input-group">
                                                <input class="form-control" type="text"
                                                       placeholder="0000 0000 0000 0000">
                                                <div class="input-group-append">
                                        <span class="input-group-text">
                                            <i class="mdi mdi-credit-card"></i>
                                        </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-sm-4">
                                        <label for="ccmonth">Miesiąc</label>
                                        <select class="form-control" id="ccmonth">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                            <option>8</option>
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="ccyear">Rok</label>
                                        <select class="form-control" id="ccyear">
                                            <option>2014</option>
                                            <option>2015</option>
                                            <option>2016</option>
                                            <option>2017</option>
                                            <option>2018</option>
                                            <option>2019</option>
                                            <option>2020</option>
                                            <option>2021</option>
                                            <option>2022</option>
                                            <option>2023</option>
                                            <option>2024</option>
                                            <option>2025</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="cvv">CVV/CVC</label>
                                            <input class="form-control" id="cvv" type="text" placeholder="123">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <form action="SaleServlet" method="post">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label>Kupon Rabatowy</label>
                                                <input class="form-control" name="code" type="text"
                                                       placeholder="wprowadź kupon">
                                            </div>
                                        </div>
                                    </div>
                                    <button style="width: 500px" type="submit" name="realizeCode"
                                            value="<%=orderNumber%>"
                                            class="btn btn-primary btn-rounded btn-sm my-0">Realizuj Kupon
                                    </button>
                                </form>
                                <div class="col-md-8 center-block" style="margin-left: 80px">
                                    <form action="PaymentServlet" method="post">
                                        <input type="hidden" name="paymentOperation" value="cancel"/>
                                        <button style="min-width: 300px" type="submit" name="cancelOrder"
                                                value="<%=orderNumber%>"
                                                class="btn btn-danger btn-rounded btn-sm my-0">Anuluj zamówienie
                                        </button>
                                    </form>
                                    <form action="PaymentServlet" method="post">
                                        <input type="hidden" name="paymentOperation" value="accept"/>
                                        <button style="min-width: 300px" type="submit" name="acceptOrder"
                                                value="<%=orderNumber%>"
                                                class="btn btn-success btn-rounded btn-sm my-0">Potwierdź płatność
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>




