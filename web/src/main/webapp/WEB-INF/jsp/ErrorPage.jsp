<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Server Error</title>
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

<div class="container containerPadding">
    <div class="row">
        <div class="span12">
            <div class="errorContainer">
                <div class="middleOrientation">
                    <p class="text-center text-error strongMessage"><c:out value="${errorMessage}"/></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>