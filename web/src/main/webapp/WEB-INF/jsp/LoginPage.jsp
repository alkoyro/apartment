<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <title>Login</title>

    <style type="text/css">
        label {
            cursor: default !important;
        }
    </style>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/docs.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/prettify.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/apartment.css" rel="stylesheet">

    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>
<%@ include file="MenuComponent.jsp" %>
<%@ include file="OverviewComponent.jsp" %>

<form name="login" action="<c:url value='j_spring_security_check'/>" method="POST">
    <div class="container containerPadding">
        <div class="row">
            <div class="span3">
            </div>

            <div class="span4" style="width: 400px">

                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label" for="inputUsername">Login</label>

                        <div class="controls">
                            <input type="text" name='j_username' id="inputUsername" placeholder="Username">
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label" for="inputUsername">Password</label>

                        <div class="controls">
                            <input type="password" name='j_password' id="inputPassword" placeholder="Password">
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <div style="text-align: right">
                        <input type="submit" value="Sign in" class="btn"/>
                    </div>
                </div>

            </div>
        </div>
    </div>
</form>

</body>
</html>