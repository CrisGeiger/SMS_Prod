<%-- 
    Autor: AJ
    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
    edited: Becker 04.04.18 /WEB-INF/ in diesem und den anderen Servlets im Tag <% taglib ..%> hinzugefügt, um den Dateipfad zum Template "base" zu korrigieren. Dies führte dazu, dass nun erst klar ist für das Servlet, wie die Seite aufgebaut sein soll.
    Dieser Korrektur führte zum ersten Erscheinen der Login-Benutzerfläche.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Login
    </jsp:attribute>

    <jsp:attribute name="head">

        <link rel="stylesheet" href="<c:url value="/css/login.css"/> "/>

    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/signup/"/>">Registrieren</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
            <form action="security_check" method="post" class="stacked">
                <div class="column">
                    <%-- Eingabefelder --%>
                    <label for="username">
                        Benutzername:
                        <span class="required">*</span>
                    </label>
                    <input type="text" name="username">

                    <label for="password">
                        Passwort:
                        <span class="required">*</span>
                    </label>
                    <input type="password" name="password">

                    <%-- Button zum Abschicken --%>
                    <button class="icon-login" type="submit">
                        Einloggen
                    </button>
                </div>
            </form>
        </div>
    </jsp:attribute>
</template:base>
