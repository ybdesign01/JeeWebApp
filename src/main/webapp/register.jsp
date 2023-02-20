<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:useBean id="message" scope="request" class="java.lang.String"/>
<html>

<head>

    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>

<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <h3 class="mb-5">Sign up</h3>
                        <form action="${pageContext.request.contextPath}/register" method="post" id="form">
                            <div class="mb-3">
                                <label for="name" class="form-label">Nom complet </label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Entrer votre nom complet" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email </label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Entrer votre email" required>
                            </div>
                            <div class="mb-3">
                                <label for="pwd" class="form-label" >Password </label>
                                <input type="password" class="form-control" name="pwd" id="pwd" placeholder="Entrer votre mot de passe" required>
                            </div>
                            <div class="mb-3">
                                <label for="pwdconf" class="form-label" >Password </label>
                                <input type="password" class="form-control" name="pwdconf" id="pwdconf" placeholder="Entrer votre mot de passe" required>
                            </div>
                            <c:if test="${!empty message}">
                                <div class="alert alert-info" role="alert" id="successAlert">
                                    ${message}
                                </div>
                            </c:if>

                        <div class="d-grid">
                            <input class="btn btn-primary btn-login fw-bold mb-2" name="action" value="Register" type="submit">
                            <a class="btn btn-outline-success btn-login fw-bold mb-2" href="${pageContext.request.contextPath}/auth">Sign in</a>

                        </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


</body>
</html>