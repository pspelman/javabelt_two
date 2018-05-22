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
    <div class="foreground">

        <c:choose>
            <c:when test="${currentUser.equals(idea.getAddedBy())}">
                <div>
                    Update your idea:
                    <h1>current detail: ${idea.title}</h1>

                    <c:if test="${errorMessage != null}">
                        <c:out value="${errorMessage}"></c:out>
                    </c:if>
                    <%--<form:form method="POST" action="/ideas/edit/${idea.id}" modelAttribute="idea" cssClass="user_form">--%>
                    <form:form method="POST" action="/ideas/edit/" modelAttribute="idea" cssClass="user_form">

                        <form:label path="title">Title</form:label>
                        <form:input path="title" /><br/>
                        <form:errors path="title" cssClass="alert-danger" />

                        <form:hidden path="addedByUserId" name="added_by_id" value="${currentUser.getId()}" />
                        <form:hidden path="addedBy" name="addedBy" value="${currentUser.getId()}" />
                        <form:hidden path="id" name="id" value="${idea.getId()}" />

                        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

                        <input type="submit" class="btn-inverse" value="Update"/>

                    </form:form>
                </div>
            </c:when>
            <c:otherwise>
                <div>
You do not have permission to edit this idea
                </div>
            </c:otherwise>
        </c:choose>






    </div>

</div>

<br/>
</body>
</html>