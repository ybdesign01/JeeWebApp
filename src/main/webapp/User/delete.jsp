<jsp:useBean id="currentNote" scope="request" type="estm.dsic.models.Note"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
        body {
            height: 100%;
            margin: 0;
            background: #F5F5F5;
        }
    </style>
<meta charset="ISO-8859-1">
<title>Delete note</title>
</head>
<body>
<br>
<div class="card  mx-5 my-5">
    <div class="card-body d-flex justify-content-between">
        <p class="lead">
            Welcome back,  ${sessionScope.user.getName()}
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
        <form action="${pageContext.request.contextPath}/deleteNote" method="post" id="form">
            <div class="mb-3 text-center ">
                <p class="lead">Are you sure you want to delete the note ?</p>
                <input type="text" id="id" name="id" value="${currentNote.id}" hidden="hidden">
                <input class="btn btn-danger fw-bold mb-2" name="action" value="Delete" type="submit">
            </div>

        </form>
    </div>
</div>
</body>
</html>