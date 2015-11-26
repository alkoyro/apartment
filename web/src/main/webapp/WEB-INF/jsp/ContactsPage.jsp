<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <title>Contacts</title>
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

            <div class="row">
                <div class="span9">
                    <strong>Apartment, Inc.</strong>
                </div>
            </div>

            <div class="row">
                <div class="span9">
                    StreetNumber StreetName
                </div>
            </div>

            <div class="row">
                <div class="span9">
                    Minsk, Belarus 200400
                </div>
            </div>
            <div class="row">
                <div class="span9">
                    <abbr title="Phone">P:</abbr> +375 (29) 456-7890
                </div>
            </div>

            <br/>

            <div class="row">
                <div class="span9">
                    <strong>Full Name</strong>
                </div>
            </div>

            <div class="row">
                <div class="span9">
                    <a href="mailto:first.last@example.com?subject=ApartmentFeedBack">first.last@example.com</a>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>