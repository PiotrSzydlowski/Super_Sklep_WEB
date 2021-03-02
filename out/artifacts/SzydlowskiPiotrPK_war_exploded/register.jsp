<%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nowy Uzytkownik</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="container-fluid mt-5">
    <div class="row mt-3">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <%@include file="components/message.jsp"%>
                <%@include file="components/validateForm.jsp"%>
                <div class="card=body px-3">
                    <div>
                        <img src="" style="max-width: 100px;" class="img-fluid">
                    </div>
                    <h2 class="text-center my-3">Utwórz konto</h2>
                    <form action="RegisterServlet" method="post">
                        <div class="form-group">
                            <label for="name">Nazwa Użytkownika</label>
                            <input name="user_name" type="text" class="form-control" id="name" placeholder="Wprowadź nazwę użytkownika"
                                   aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input name="user_email" type="email" class="form-control" id="email" placeholder="Wprowadź adres email"
                                   aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="password">Hasło</label>
                            <input name="user_password" type="password" class="form-control" id="password" placeholder="Wprowadź hasło"
                                   aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="password">Powtórz hasło</label>
                            <input name="user_repet_password" type="password" class="form-control" id="repet_password" placeholder="Powtórz hasło"
                                   aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="phone">Telefon</label>
                            <input name="user_phone" type="number" class="form-control" id="phone" placeholder="Wprowadź numer telefonu"
                                   aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="phone">Adres</label>
                            <textarea name="user_address" style="height: 60px " class="form-control"
                                      placeholder="Wprowadź adres"></textarea>
                        </div>
                        <div class="container text-center">
                            <button class="btn btn-outline-success">Zarejestruj</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
