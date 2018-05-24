<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
    <title>Welcome Page</title>

    <script type="javascript">
        var setLikeButtonText = function() {
            console.log(`like button text setting`,);

            document.getElementById("target").innerText = "POOP";
        }
    </script>
</head>
<body>
${search_errors}
<div class="container">
    <t:generic_navbar>
        <jsp:attribute name="top_nav">
        </jsp:attribute>
    </t:generic_navbar>

    <br/>
    <div id="target">

    </div>
    <div class="idea_info">

        <%--<h2>Heading: ${heading_message}</h2>--%>
        <h2>Idea: ${idea.title}</h2>
        <c:choose>
            <c:when test="${currentUser.equals(idea.getAddedBy())}">
                <div>
                    You submitted this
                    <button name="edit" class="table-btn btn-inverse" value="Edit" onclick="location.href='/ideas/edit/${idea.id}';">Edit Details</button>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    Submitted by: ${idea.getAddedBy().getUsername()}
                </div>
            </c:otherwise>
        </c:choose>


        <br/>
        <div class="foreground">
            <h3>Users who liked this idea</h3>
            <br/>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <%--<th scope="col">--%>
                    <%--#--%>
                    <%--</th>--%>
                    <%--<th scope="col">--%>
                        <%--User--%>
                    <%--</th>--%>
                    <%--<th scope="col">--%>
                        <%--Rating--%>
                    <%--</th>--%>

                </tr>
                </thead>
                <tbody>
                <%--for loop can go here, or in the tr tag --%>
                <c:forEach var="liker" items="${likevotesList}" varStatus="loop" >
                    <tr>
                            <%--<th scope="row">--%>
                            <%--${loop.index+1}--%>
                            <%--</th>--%>
                        <th scope="row">
                                <%--${likesList.getUser().getUsername()}</a>--%>
                                <%--${idea.name}--%>
                            ${liker.getUser().getUsername()}

                        </th>
                        <%--<td>--%>
                            <%--this will be the total like--%>
                                <%--&lt;%&ndash;${likesList.getRating()}&ndash;%&gt;--%>

                        <%--</td>--%>

                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>


    </div>
    <%--<div class="likevote_form">--%>
        <%--<form:form method="POST" action="/ideas/${idea.id}/likevote" modelAttribute="userIdea" cssClass="user_form">--%>
            <%--<form:select path="likevote" items="${likevotes}"></form:select>--%>
            <%--<form:hidden path="idea" id="idea" name="idea" value="${idea.id}"/>--%>
            <%--<form:hidden path="user" id="user" name="user" value="${currentUser.getId()}"/>--%>
            <%--&lt;%&ndash;<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>&ndash;%&gt;--%>

            <%--<input type="submit" class="btn-inverse" value="Rate"/>--%>

        <%--</form:form>--%>

    <c:choose>

        <c:when test="${currentUser.equals(idea.getAddedBy())}">

            <div>
            You submitted this (cannot like / unlike)
            <%--<button name="edit" class="table-btn btn-inverse" value="Edit" onclick="location.href='/ideas/edit/${idea.id}';">Edit Details</button>--%>
            </div>

        </c:when>

    <%-- THIS is if the idea was NOT submitted by the logged in user --%>
        <c:otherwise>
            <div>
        <c:set var="contains" value="false" />
            <c:forEach var="likevote" items="${likevotesList}">
                <c:if test="${likevote.getUser().getId().equals(currentUser.getId())}">
                    <%--<c:out value="YOU LIKED THIS"/>--%>
                    <c:set var="contains" value="true"/>
                    <%--<script>--%>
                        <%--setLikeButtonText();--%>
                    <%--</script>--%>
                </c:if>
        </c:forEach>
                <c:choose>
                    <c:when test="${contains}">
                        <script>
                            document.getElementById("likevoteButton").value = "newButtonValue";
                        </script>
                        <button name="rate" class="table-btn btn-inverse" value="Edit" onclick="location.href='/ideas/likevote/${idea.id}';">Unlike</button>
                    </c:when>
                    <c:otherwise>
                        <button name="rate" class="table-btn btn-inverse" value="Edit" onclick="location.href='/ideas/likevote/${idea.id}';">Like</button>
                    </c:otherwise>

                </c:choose>
                <div id="buttonspace">
                </div>

            <%--<c:if test="${contains==true}" >--%>
                <div>
                <%--You liked this--%>
                </div>
        <div>
        <%--you have not liked this--%>
        </div>
        </c:otherwise>
    </c:choose>

    </div>

    <%--<c:when test="${likevotesList.contains(currentUser)}">--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
    <%--<div>--%>
    <%--You have not liked this<br/>--%>
    <%--&lt;%&ndash;<button name="rate" class="table-btn btn-inverse" value="Edit" onclick="location.href='/ideas/likevote/${idea.id}';">Like</button>&ndash;%&gt;--%>
    <%--</div>--%>
    <%--</c:otherwise>--%>
    <%--</c:choose>--%>
    <%--&lt;%&ndash;<button name="rate" class="table-btn btn-inverse" value="Edit" onclick="location.href='/ideas/likevote/${idea.id}';">Like</button>&ndash;%&gt;--%>
    <%--</div>--%>

</div>
</body>
</html>