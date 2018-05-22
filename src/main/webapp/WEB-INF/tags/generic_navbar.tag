<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="top_nav" fragment="true" %>
<%@attribute name="page_header" fragment="true" %>

<HTML>

<body>
<div class="top_nav">
    <jsp:invoke fragment="top_nav" />
    <button name="index" class="btn-inverse" value="index" onclick="location.href='/index';">TestDx</button>
    <button name="home" class="btn-inverse" value="home" onclick="location.href='/home';">Home</button>
    <sec:authorize access="isAnonymous()">
        <%--NOT LOGGED IN--%>
        <button name="login" class="btn-inverse" value="login" onclick="location.href='/login';">Login</button>
        <button name="add_registration" class="btn-inverse" value="add_registration" onclick="location.href='/registration';">Register</button>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <%--IS LOGGED IN--%>

        <button name="add_idea" class="btn-inverse" value="add_idea" onclick="location.href='/ideas/new';">New Idea</button>
        <form id="logoutForm" method="POST" action="/logout" class="inline" style="display: inline;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Logout" class="btn-inverse inline" />
        </form>
    </sec:authorize><br/>
    <sec:authorize access="isAuthenticated()">
        <c:out value="logged in"></c:out>
        <%--<c:out value="logged in as ${currentUser.getUsername()}"></c:out>--%>
    </sec:authorize>
</div>
</body>
</HTML>