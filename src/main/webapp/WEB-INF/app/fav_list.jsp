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
            <a href="<c:url value="/app/spots"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">

        <%-- Gefundene Aufgaben --%>
        <c:choose>
            <c:when test="${empty favorites}">
                <p>
                    Es wurden keine Favoriten gefunden. 🐈
                </p>
            </c:when>
            <c:otherwise>
                <jsp:useBean id="utils" class="sharemyspot_prod.web.WebUtils"/>
                
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