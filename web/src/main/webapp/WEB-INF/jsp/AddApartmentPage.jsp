<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <title>Add Apartment</title>

    <style type="text/css">
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
    <link href="${pageContext.request.contextPath}/css/bootstrap-fileupload.css" rel="stylesheet">

    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-fileupload.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/apartment.js" type="text/javascript"></script>
</head>

<body>
<%@ include file="MenuComponent.jsp" %>
<%@ include file="OverviewComponent.jsp" %>


<div class="container containerPadding">
    <form:form action="applyApartmentChanges.html" method="post" commandName="apartmentForm"
               enctype="multipart/form-data">
        <div class="row">
            <div class="span3">
            </div>

            <div class="span4" style="width: 400px">

                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label" for="inputPrice">Price (USD)</label>

                        <div class="controls">
                            <form:input path="price" id="inputPrice" maxlength="4"/>
                            <div style="padding-left: 1px">
                                <form:errors cssClass="text-error" path="price"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label" for="inputCity">City</label>

                        <div class="controls">
                            <form:input path="city" id="inputCity" maxlength="15"/>
                            <div style="padding-left: 1px">
                                <form:errors cssClass="text-error" path="city"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label" for="inputStreet">Street</label>

                        <div class="controls">
                            <form:input path="street" id="inputStreet" maxlength="40"/>
                            <div style="padding-left: 1px">
                                <form:errors cssClass="text-error" path="street"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label" for="inputHouseNumber">House number</label>

                        <div class="controls">
                            <div class="form-inline">
                                <form:input path="houseNumber" id="inputHouseNumber" maxlength="3" cssClass="input-mini"
                                            cssStyle="width: 113px;"/>
                                <label for="inputBuildingNumber">/</label>
                                <form:input path="buildingNumber" id="inputBuildingNumber" maxlength="3"
                                            cssClass="input-mini"
                                            cssStyle="width: 63px; float: right;"/>
                            </div>
                            <div style="padding-left: 1px">
                                <form:errors cssClass="text-error" path="houseNumber"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label" for="inputRoomCount">Room count</label>

                        <div class="controls">
                            <form:input path="roomCount" id="inputRoomCount" maxlength="2"/>
                            <div style="padding-left: 1px">
                                <form:errors cssClass="text-error" path="roomCount"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label" for="inputSquare">
                            Square (m<sup>2</sup>)
                        </label>

                        <div class="controls">
                            <form:input path="square" id="inputSquare" maxlength="3"/>
                            <div style="padding-left: 1px">
                                <form:errors cssClass="text-error" path="square"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label" for="inputDescription">Description</label>

                        <div class="controls">
                            <form:textarea path="description" id="inputDescription"/>
                            <div style="padding-left: 1px">
                                <form:errors cssClass="text-error" path="description"/>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="control-group">
                    <div class="form-horizontal">
                        <label class="control-label">Images</label>

                        <div class="controls">

                            <table id="fileTable">
                                <tr>
                                    <td>
                                        <div class="fileupload fileupload-new" data-provides="fileupload">
                                        <span class="btn btn-file" style="width: 195px;">
                                            <span class="fileupload-new">Select file</span>
                                            <span class="fileupload-exists">Change</span>
                                            <input name="files[0]" type="file"/>
                                        </span>
                                            <span class="fileupload-preview"></span>
                                            <a href="#" class="close fileupload-exists" data-dismiss="fileupload"
                                               style="float: none">×</a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <div style="text-align: right">
                        <input id="addFile" type="button" value="Add more files" class="btn my-btn btn-info"/>
                    </div>
                </div>

                <div class="control-group">
                    <div style="text-align: right">
                        <button type="submit" class="btn my-btn btn-info">
                            OK
                        </button>
                        <a class="btn my-btn btn-info" href="<c:url value="/management/allApartments.html" />">
                            Cancel
                        </a>
                    </div>
                </div>

            </div>
        </div>
    </form:form>
</div>

</body>
</html>