<%-- 
    Document   : user_edit
    Created on : 26.03.2018, 10:21:45
    Author     : Budda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Benutzerprofil bearbeiten
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/spots"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
            <form method="post" class="stacked">
                <div class="column">
                    <%-- CSRF-Token --%>
                    <input type="hidden" name="csrf_token" value="${csrf_token}">
                    <h2>Logindaten</h2>        
                    <%-- Eingabefelder --%>
                    <label for="edit_username">
                        Benutzername:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_username" value="${edit_form.values["edit_username"][0]}" >
                    </div>
                    
                    <label for="edit_oldpw">
                        old Passwort:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="edit_oldpw" value="${edit_form.values["edit_oldpw"][0]}">
                    </div>
                    
                    <label for="edit_password1">
                        Passwort (PW ändern sonst "default"):
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="edit_password1" value="${edit_form.values["edit_password1"][0]}">
                    </div>

                    <label for="edit_password2">
                        Passwort (wdh.):
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="edit_password2" value="${edit_form.values["edit_password2"][0]}">
                    </div>
                    
                    <h2>Anschrift</h2>
                    <label for="edit_lastName">
                        Nachname:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_lastName" value="${edit_form.values["edit_lastName"][0]}">
                    </div>
                    <label for="edit_firstName">
                        Vorname:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_firstName" value="${edit_form.values["edit_firstName"][0]}">
                    </div>
                    
                    <label for="edit_road">
                        Strassenname:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_road" value="${edit_form.values["edit_road"][0]}">
                    </div>
                    <label for="edit_roadNumber">
                        Hausnummer:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_roadNumber" value="${edit_form.values["edit_roadNumber"][0]}">
                    </div>
                    
                    <label for="edit_place">
                        Ort:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_place" value="${edit_form.values["edit_place"][0]}">
                    </div>
                    
                    <label for="edit_plz">
                        PLZ:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_plz" value="${edit_form.values["edit_plz"][0]}">
                    </div>
                    
                    <h2>Kontaktdaten</h2>
                    <label for="edit_phoneNumber">
                        Telefonnummer:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_phoneNumber" value="${edit_form.values["edit_phoneNumber"][0]}">
                    </div>
                    
                    <label for="edit_email">
                        E-Mail:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_email" value="${edit_form.values["edit_email"][0]}">
                    </div>
                    
                    <%-- Button zum Abschicken --%>
                    <div class="side-by-side">
                        <button class="icon-pencil" type="submit">
                            Ändern
                        </button>
                    </div>
                </div>
                <%-- Fehlermeldungen --%>
                <c:if test="${!empty edit_form.errors}">
                    <ul class="errors">
                        <c:forEach items="${edit_form.errors}" var="error">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </c:if>
            </form>
        </div>
    </jsp:attribute>
</template:base>