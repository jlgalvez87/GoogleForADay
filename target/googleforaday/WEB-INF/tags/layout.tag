<!DOCTYPE html>
<%@ tag description="Simple Template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="css_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>

<!-- HTML CSS URL -->
<c:url value="/resources/css/layout_style.css" var="APP_CSS"/>

<!-- HTML PATH URL -->
<c:url value="/" var="APP_HOME"/>
<c:url value="/search/" var="URL_SEARCH_WORD"/>

<html>
<head>

    <title>Google for a Day</title>

    <link type="text/css" href="<c:out value="${APP_CSS}"/>" rel="stylesheet"/>
    <jsp:invoke fragment="css_area"/>

</head>
<body>

    <div id="container">

        <div id="nav-panel">
            <ul class="nav-ul">
                <li><a href="<c:out value="${URL_SEARCH_WORD}"/>">Search</a></li>
                <li><a href="<c:out value="${APP_HOME}"/>">Index</a></li>
            </ul>
        </div>

        <div id="content-pane">
            <jsp:invoke fragment="body_area"/>
        </div>

    </div>

</body>
</html>