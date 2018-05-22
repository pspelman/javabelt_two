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

    <title>Registration Page</title>
</head>
<body>
<div class="container">
    <t:generic_navbar>
                <jsp:attribute name="top_nav">
                </jsp:attribute>
    </t:generic_navbar>
    <br/>

    <h1>Register!</h1>
    <br/>
    <%--<p><form:errors path="user.*"/></p>--%>

    <form:form method="POST" action="/registration" modelAttribute="user">
        <p>
            <form:label path="username">Username:</form:label>
            <form:input path="username"/><br/>
            <form:errors path="username" cssClass="alert-danger" />

        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input path="email"/><br/>
            <form:errors path="email" cssClass="alert-danger" />

        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password" /><br/>
            <form:errors path="password" cssClass="alert-danger" />

        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/><br/>
            <form:errors path="passwordConfirmation" cssClass="alert-danger" />

        </p>
        <input type="submit" class="btn-inverse" value="Register!"/>
    </form:form>
</div>

</body>
</html>