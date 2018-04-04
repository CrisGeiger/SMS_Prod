<%-- 
    Document   : fav_list
    Created on : 26.03.2018, 09:35:21
    Author     : Budda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Favoriten
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/spot_list.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/spots/new/"/>">Parkplatz anlegen</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/app/spots/"/>">Zürück zur Spotliste</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <%-- Suchfilter --%>
        <form method="GET" class="horizontal" id="search">
            <input type="text" name="search_text" value="${param.search_text}" placeholder="Beschreibung"/>

            <select name="search_category">
                <option value="">Alle Kategorien</option>

                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" ${param.search_category == category.id ? 'selected' : ''}>
                        <c:out value="${category.name}" />
                    </option>
                </c:forEach>
            </select>

            <select name="search_status">
                <option value="">Alle Stati</option>

                <c:forEach items="${statuses}" var="status">
                    <option value="${status}" ${param.search_status == status ? 'selected' : ''}>
                        <c:out value="${status.label}"/>
                    </option>
                </c:forEach>
            </select>

            <button class="icon-search" type="submit">
                Suchen
            </button>
        </form>

        <%-- Gefundene Aufgaben --%>
        <c:choose>
            <c:when test="${empty favorites}">
                <p>
                    Es wurden keine Favoriten gefunden. 🐈
                </p>
            </c:when>
            <c:otherwise>
                <jsp:useBean id="utils" class="sharemyspot.web.WebUtils"/>
                
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Owner</th>
                            <th>Ort</th>
                            <th>Plz</th>
                            <th>Straße</th>
                            <th>Hausnummer</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <c:forEach items="${favorites}" var="task">
                        <tr>
                            <td>
                                <c:out value="${favorite.id}"/>
                            </td>
                            <td>
                                <c:out value="${favorite.spot.owner}"/>
                            </td>
                            <td>
                                <c:out value="${favorite.spot.ort}"/>
                            </td>
                            <td>
                                <c:out value="${favorite.spot.plz}"/>
                            </td>
                            <td>
                                <c:out value="${favorite.spot.road}"/>
                            </td>
                            <td>
                                <c:out value="${favorite.spot.roadnumber}"/>
                            </td>
                            <td>
                                <c:out value="${favorite.spot.plz}"/>
                            </td>
                            <td>
                                <c:out value="${favorite.spot.status}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>