<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button class="btn btn-navbar" data-target=".nav-collapse" data-toggle="collapse" type="button">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="${pageContext.request.contextPath}/allApartments.html">
                Apartments
            </a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/allApartments.html">
                            Home
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/contacts.html">
                            Contacts
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/management/allApartments.html">
                            Administration
                        </a>
                    </li>
                    <sec:authorize access="isAuthenticated()">
                        <li>
                            <a href="${pageContext.request.contextPath}/management/addApartment.html">
                                Add apartment
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li>
                            <a href="${pageContext.request.contextPath}/management/logout.html">
                                Logout
                            </a>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </div>
</div>