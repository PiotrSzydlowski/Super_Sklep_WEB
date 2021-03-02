<%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-bg">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <div style="margin-right: 300px; margin-bottom: 15px; margin-top: 15px" class="btn-group" role="group" aria-label="Basic example">
                   <a href="userData.jsp"><button style="min-height: 60px; min-width: 200px; margin-left: 30px; background: gray" type="button" class="btn btn-secondary">Dane</button></a>
                    <a href="userPassword.jsp"><button style="min-height: 60px; min-width: 200px; margin-left: 30px; background: gray" type="button" class="btn btn-secondary">Hasło</button></a>
                    <a href="userHistory.jsp"><button style="min-height: 60px; min-width: 200px; margin-left: 30px; background: gray" type="button" class="btn btn-secondary">Zakupy</button></a>
                    <a href="userOrders.jsp"><button style="min-height: 60px; min-width: 200px; margin-left: 30px; background: gray" type="button" class="btn btn-secondary">Zamówienia w realizcji</button></a>
                </div>
            </ul>
        </div>
    </div>
</nav>