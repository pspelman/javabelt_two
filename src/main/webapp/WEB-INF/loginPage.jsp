<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>

    <title>Login Page</title>
</head>
<body>
<div class="container">
    <t:generic_navbar>
                <jsp:attribute name="top_nav">
                </jsp:attribute>
    </t:generic_navbar>
    <br/>
    <c:if test="${logoutMessage != null}">
        <c:out value="${logoutMessage}"></c:out>
    </c:if>
    <h1>Login</h1>
    <c:if test="${errorMessage != null}">
        <c:out value="${errorMessage}"></c:out>
    </c:if>
    <form:form method="POST" action="/login" modelAttribute="user" cssClass="user_form">

        <form:label path="username">Username</form:label>
        <form:input path="username" /><br/>
        <form:errors path="username" cssClass="alert-danger" />

        <form:label path="password">Password</form:label>
        <form:password path="password" /><br/>
        <form:errors path="password" cssClass="alert-danger" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <input type="submit"/>
    </form:form>

</div>

<br/>
</body>
</html>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
    <%--pageEncoding="UTF-8"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--<title>Login Page</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>
    <%--<h1>loginPage page</h1>--%>

<%--</div>--%>
    <%--&lt;%&ndash;<c:if test="${errorMessage != null}">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<c:out value="${errorMessage}"></c:out>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<form:form method="POST" action="/login" modelAttribute="user" cssClass="user_form">&ndash;%&gt;--%>

        <%--&lt;%&ndash;<form:label path="username">Username</form:label>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:input path="username" /><br/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:errors path="username" cssClass="alert-danger" />&ndash;%&gt;--%>

        <%--&lt;%&ndash;<form:label path="password">Password</form:label>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:password path="password" /><br/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:errors path="password" cssClass="alert-danger" />&ndash;%&gt;--%>
        <%--&lt;%&ndash;<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<input type="submit"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</form:form>&ndash;%&gt;--%>


<%--</body>--%>
<%--</html>--%>