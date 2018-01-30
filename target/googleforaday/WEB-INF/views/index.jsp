<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- HTML CSS URL -->
<c:url value="/resources/css/index_style.css" var="SEARCH_CSS"/>

<!-- HTML PATH URL -->
<c:url value="/addUrl" var="URL_INDEX_URL"/>
<c:url value="/clearAll" var="URL_CLEAR_ALL"/>

<t:layout>

    <jsp:attribute name="css_area">
        <link type="text/css" href="<c:out value="${SEARCH_CSS}"/>" rel="stylesheet"/>
    </jsp:attribute>

    <jsp:attribute name="body_area">

        <div id="index-panel">

            <div id="form-panel">

                <form:form id="form_add" method="post" action="${URL_INDEX_URL}"/>
                <form:form id="form_clear" method="post" action="${URL_CLEAR_ALL}"/>

                <div class="form-text">
                    <input form="form_add" name="url" type="text" class="word-text"/>
                </div>

                <div class="form-buttons">
                    <input form="form_add" type="submit" class="button-index pure-button" value="Index"/>
                    <input form="form_clear" type="submit" class="button-clear pure-button" value="Clear"/>
                </div>

            </div>

            <div id="response-panel">

                <c:if test="${not empty newIndexedPage and not empty newIndexedWord}">
                    <p>Indexed <c:out value="${newIndexedPage}"/> new pages and <c:out value="${newIndexedWord}"/> words.</p>
                </c:if>

                <c:if test="${not empty allIndexedPage and not empty allIndexedWord}">
                    <h2>Indexed Pages Total: <c:out value="${allIndexedPage}"/>, Words Total: <c:out value="${allIndexedWord}"/></h2>
                </c:if>
            </div>

        </div>

    </jsp:attribute>

</t:layout>