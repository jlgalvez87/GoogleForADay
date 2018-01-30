<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- HTML CSS URL -->
<c:url value="/resources/css/search_style.css" var="SEARCH_CSS"/>

<!-- HTML PATH URL -->
<c:url value="/search/word" var="URL_SEARCH_WORD"/>

<t:layout>

    <jsp:attribute name="css_area">
        <link type="text/css" href="<c:out value="${SEARCH_CSS}"/>" rel="stylesheet"/>
    </jsp:attribute>

    <jsp:attribute name="body_area">
        <div>
            <form:form id="form_search" method="post" action="${URL_SEARCH_WORD}"/>

            <ul class="nav-ul">
                <li><input form="form_search" name="word" type="text" class="word-text"/></li>
                <li><input form="form_search" name="word-btn" type="submit" class="button-index pure-button"
                           value="Search"/></li>
            </ul>

        </div>

        <div id="search-response">
            <c:if test="${not empty occurrences}">
                <div><c:out value="Found ${occurrences} results"/></div>
                <c:forEach items="${links}" var="link">
                    <div>
                        <a href="${link.link}" target="_blank">${link.title}</a>
                        <p>Ocurrences: <strong>${link.occurrences}</strong></p>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </jsp:attribute>
</t:layout>
