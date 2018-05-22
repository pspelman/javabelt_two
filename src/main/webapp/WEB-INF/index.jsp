<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>

    <title></title>
</head>
<body>
${search_errors}
<div class="container">
    <t:generic_navbar>
                <jsp:attribute name="top_nav">
                </jsp:attribute>
    </t:generic_navbar>
    <br/>

    <div class="foreground">
        <h3></h3>
        <div>
            This is a test div!<br/>
            <sec:authorize access="isAnonymous()">
                User is NOT logged in
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                User IS logged in
                <%--<a href="<c:url value="/logout" />">Logout</a>--%>
            </sec:authorize>
        </div>
    </div>

</div>
</body>
</html>

