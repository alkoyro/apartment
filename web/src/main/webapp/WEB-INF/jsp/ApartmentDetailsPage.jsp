<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <title>Apartment</title>

    <style type="text/css">
        input {
            cursor: default !important;
        }

        label {
            cursor: default !important;
            padding-top: 2px !important;
        }
    </style>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/docs.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/prettify.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/apartment.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/lightbox.css" rel="stylesheet" media="screen">

    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/lightbox.js" type="text/javascript"></script>
</head>

<body>
<%@ include file="MenuComponent.jsp" %>
<%@ include file="OverviewComponent.jsp" %>

<div class="container containerPadding">
    <div class="row apartmentContainer">
        <div class="span3 imageContainer">
            <img width="220" height="160" alt="Apartment"
                 src="${pageContext.request.contextPath}/${apartment.subnailPath}">
        </div>

        <div class="span7">
            <div class="row">
                <div class="span7" style="padding-top: 8px">
                    <h5>
                        <c:out value="Apartment №${apartment.count}"/>
                    </h5>
                </div>
            </div>
            <div class="row">
                <div class="span7 description" style="padding-top: 8px">
                    <c:out value="${apartment.description}"/>
                </div>
            </div>

            <div class="row apartmentDetailsForm">
                <div class="span7">

                    <div class="control-group">
                        <div class="form-horizontal">
                            <label class="control-label" for="inputCity">City</label>

                            <div class="controls">
                                <input value="${apartment.city}" id="inputCity" disabled="true"/>
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-horizontal">
                            <label class="control-label" for="inputStreet">Street</label>

                            <div class="controls">
                                <input value="${apartment.street}" id="inputStreet" disabled="true"/>
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-horizontal">
                            <label class="control-label" for="inputHouseNumber">House number</label>

                            <div class="controls">
                                <input value="${apartment.houseNumber}" id="inputHouseNumber" disabled="true"/>
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-horizontal">
                            <label class="control-label" for="inputRoomCount">Room count</label>

                            <div class="controls">
                                <input value="${apartment.roomCount}" id="inputRoomCount" disabled="true"/>
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="form-horizontal">
                            <label class="control-label" for="square">
                                Square (m<sup>2</sup>)
                            </label>

                            <div class="controls">
                                <input value="${apartment.square}" id="square" disabled="true"/>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="span1 costSpan">
            <h4><c:out value="${apartment.price} USD"/></h4>
        </div>

    </div>

    <div class="row apartmentContainer">
        <c:forEach items="${apartment.apartmentImages}" var="apartmentImage">
            <div class="span3 imageContainer" style="width: 265px">
                <a href="${apartmentImage.imagePath}" rel="lightbox">
                    <img src="${apartmentImage.imageSubnailPath}"/>
                </a>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>