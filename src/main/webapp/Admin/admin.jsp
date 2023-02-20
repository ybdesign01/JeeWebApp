<jsp:useBean id="message" scope="session" class="java.lang.String"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <style>
        body {
            height: 100%;
            margin: 0;
            background: #F5F5F5;
        }
    </style>
</head>
<body>

<c:if test="${!empty message}">
    <div class="alert alert-info alert-dismissible fade show" role="alert" id="successAlert">
            ${sessionScope.message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
        System.out.println(session.getAttribute("message"));
        session.removeAttribute("message");
    %>
</c:if>


<div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="logoutModalLabel">Logout</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to log out?
            </div>
            <div class="modal-footer d-flex align-content-center justify-content-center">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <input class="btn btn-danger" type="submit" name="action" value="Yes"/>
                </form>
            </div>
        </div>
    </div>
</div>



<div class="card  mx-5 my-5">
    <div class="card-body d-flex justify-content-between">
        <p class="lead">
            Welcome back, ${user.getName()}
        </p>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#logoutModal">
            Logout
        </button>
    </div>
</div>
<div class="card mx-5 my-5">
    <div class="card-header">
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand">Users</a>
               <%-- <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>--%>
            </div>
        </nav>
    </div>
    <div class="card-body">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Email</th>
                <th scope="col">Name</th>
                <th scope="col">isAdmin</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="users" scope="request" class="java.util.Vector"/>
            <c:forEach items="${users}" var="us">
                <tr>
                    <td>
                        <p>${us.getName()}</p>
                    </td>
                    <td>
                        <p>${us.getEmail()}</p>
                    </td>
                    <td>
                        <p>${us.isAdmin()}</p>
                    </td>
                    <td>
                        <div class="d-flex">
                            <form method="post" action="${pageContext.request.contextPath}/edit">
                                <button type="submit" class="btn btn-primary mx-4" name="editBtn"
                                        value="${us.getEmail()}">Edit
                                </button>
                            </form>
                            <form method="post" action="${pageContext.request.contextPath}/delete">
                                <button type="submit" class="btn btn-danger mx-4" name="deleteBtn"
                                        value="${us.getEmail()}">Delete
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>