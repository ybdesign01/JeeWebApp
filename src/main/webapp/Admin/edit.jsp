<jsp:useBean id="currentUser" scope="request" type="estm.dsic.models.User"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style>
        body {
            height: 100%;
            margin: 0;
            background: #F5F5F5;
        }
    </style>
<meta charset="ISO-8859-1">
<title>Delete user</title>
</head>
<body>


<!-- Modal -->
<div class="modal fade" id="EditModal" tabindex="-1" aria-labelledby="EditModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="EditModalLabel">User</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <input type="text" id="currentEmail">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<br>
<div class="card  mx-5 my-5">
    <div class="card-body d-flex justify-content-between">
        <p class="lead">
            Welcome back,  ${user.getName()}
        </p>
        <div class="d-flex">
            <form action="${pageContext.request.contextPath}/auth" method="post">
                <input class="btn btn-success" type="submit" name="action" value="Back"/>
            </form>
        </div>
    </div>
</div>
<div class="card mx-5 my-5">
    <div class="card-header">
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand">Edit user</a>
            </div>
        </nav>
    </div>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/editUser" method="post" id="form">
            <div class="mb-3">
                <label for="name" class="form-label">Nom complet </label>
                <input type="text" class="form-control" id="name" name="name" value="${currentUser.name}" placeholder="Entrer le nom complet" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email </label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Entrer votre email" value="${currentUser.email}" required>
            </div>
            <div class="mb-3">
                <label for="pwd" class="form-label" >Password </label>
                <input type="password" class="form-control" name="pwd" id="pwd" placeholder="Entrer votre mot de passe" value="${currentUser.pwd}" required>
            </div>
            <div class="mb-3">

                <div class="form-check">
                    <input class="form-check-input" type="radio" value="admin" name="isAdmin" id="isAdmin" <c:if test="${currentUser.admin}">checked</c:if>>
                    <label class="form-check-label" for="isAdmin">
                        Admin
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" value="user" name="isAdmin" id="isUser" <c:if test="${!currentUser.admin}">checked</c:if>>
                    <label class="form-check-label" for="isUser">
                        User
                    </label>
                </div>
            </div>

            <div class="d-grid">
                <input class="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2" name="action" value="Save" type="submit">
            </div>

        </form>
    </div>
</div>



</body>
</html>