<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>

    <title>Stuff! </title>
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
            <h3>Things and stuff</h3>
            This is the homePage page
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">
                        #
                    </th>
                    <th scope="col">
                        Title
                    </th>
                    <th scope="col">
                        Submitted by
                    </th>
                    <th scope="col">
                        Likes
                    </th>
                    <th scope="col">
                        Action
                    </th>
                </tr>
                </thead>
                <tbody>
                <%--for loop can go here, or in the tr tag --%>
                <c:forEach var="idea" items="${ideas}" varStatus="loop" >
                    <tr>
                        <th scope="row">
                                ${loop.index+1}
                        </th>
                        <th scope="row">
                            <a href="/ideas/view/${idea.id}">${idea.title}</a>
                                <%--${idea.name}--%>

                        </th>
                        <td>
                                ${idea.addedBy.getUsername()}

                        </td>
                        <td>
                                <%--<fmt:formatNumber type="CURRENCY">${idea.rating}</fmt:formatNumber>--%>
                            {LIKES HERE}
                                <%--${idea.rating}--%>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${currentUser.equals(idea.getAddedBy())}">
                                    <div>
                                        <button name="delete" class="table-btn btn-danger" value="Delete" onclick="location.href='/ideas/delete/${idea.id}';">remove</button>
                                        <button name="edit" class="table-btn btn-inverse" value="Edit" onclick="location.href='/ideas/edit/${idea.id}';">Edit Details</button>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>


    </div>
</body>
</html>

