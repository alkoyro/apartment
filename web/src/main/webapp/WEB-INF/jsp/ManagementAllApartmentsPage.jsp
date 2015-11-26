<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <title>All Managed Apartments</title>
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
            <c:choose>
                <c:when test="${empty apartments}">
                    <div class="errorContainer">
                        <div class="middleOrientation">
                            <p class="text-center strongMessage"><c:out value="Database is empty"/></p>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${apartments}" var="apartment" varStatus="apartmentStatus">
                        <c:url var="detailsUrl" value="/selectedApartmentDetails.html">
                            <c:param name="selectedApartmentId" value="${apartment.id}"/>
                            <c:param name="count" value="${apartmentStatus.count}"/>
                        </c:url>

                        <c:url var="deleteUrl" value="/management/delete.html">
                            <c:param name="apartmentId" value="${apartment.id}"/>
                        </c:url>

                        <div class="row apartmentContainer">
                            <div class="span3 imageContainer">
                                <a href='<c:out value="${detailsUrl}"/>'>
                                    <img width="220" height="160" alt="Apartment"
                                         src="${pageContext.request.contextPath}/${apartment.subnailPath}">
                                </a>
                            </div>

                            <div class="span6">
                                <div class="row">
                                    <div class="span6">
                                        <a href='<c:out value="${detailsUrl}"/>'>
                                            <h5>
                                                <c:out value="№${apartmentStatus.count}. ${apartment.shortDescription}"/>
                                            </h5>
                                        </a>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="span6 description">
                                        <c:out value="${apartment.longDescription}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="span1" style="width: 100px">
                                <h4><c:out value="${apartment.price} USD"/></h4>
                            </div>

                            <div class="span1" style="margin-top: 6px;">
                                <a class="btn my-btn btn-info" href='<c:out value="${deleteUrl}"/>'>
                                    Delete
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>