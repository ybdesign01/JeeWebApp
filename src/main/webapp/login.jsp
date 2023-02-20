<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<jsp:useBean id="message" scope="request" class="java.lang.String"/>
<!DOCTYPE html>

<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<style>
    body {
        background-image: linear-gradient(to top, #002a69, #15468d, #2663b2, #3483d8, #41a4ff);
    }
</style>
<body>

<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <h3 class="mb-5">Sign in</h3>
                        <form action="${pageContext.request.contextPath}/auth" method="post">
                            <div class="mb-3">
                                <label for="email" class="form-label">Email </label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Entrer votre email" required>
                            </div>
                            <div class="mb-3">
                                <label for="pwd" class="form-label" >Password</label>
                                <input type="password" class="form-control" name="pwd" id="pwd" placeholder="Entrer votre mot de passe" required>
                            </div>

                        <div class="d-grid d-md-block">
                            <input class="btn btn-primary btn-login fw-bold mb-2" name="action" value="Login" type="submit"/>
                                <a class="btn btn-outline-success btn-login fw-bold mb-2" href="${pageContext.request.contextPath}/register">Register</a>
                        </div>

                        </form>
                        <c:if test="${!empty message}">
                            <div class="alert alert-info" role="alert" id="successAlert">
                                    ${message}
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


</body>
</html>