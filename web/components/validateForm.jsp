<%--
  Created by IntelliJ IDEA.
  User: Piotr Szydłowski
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String validateForm = (String) session.getAttribute("validateForm");
    if (validateForm != null) {
%>
<div class="alert alert-danger alert-dismissible fade show" role="alert">
    <strong><%= validateForm%></strong>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("validateForm");
    }
%>

