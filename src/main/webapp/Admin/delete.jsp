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
<title>Edit user</title>
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
                <a class="navbar-brand">Delete user</a>
            </div>
        </nav>
    </div>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/deleteUser" method="post" id="form">
            <div class="mb-3 text-center ">
                <p class="lead">Are you sure you want to delete <b>${currentUser.email}</b> ?</p>
                <input type="email" class="form-control" id="email" name="email" value="${currentUser.email}" hidden="hidden">
                <input class="btn btn-danger fw-bold mb-2" name="action" value="Delete" type="submit">
            </div>

        </form>
    </div>
</div>

</body>
</html>