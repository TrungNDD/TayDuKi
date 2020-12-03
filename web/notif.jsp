<%-- 
    Document   : notif
    Created on : Jul 20, 2020, 1:52:38 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .notif{
        position: fixed;
        right: 50px;
        bottom: 50px;
    }
</style>

<div class="dropup notif">
    <a type="button" class="btn btn-secondary" href="notif">
        Notification
    </a>
    <button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="sr-only">Toggle Dropdown</span>
    </button>
    <div class="dropdown-menu">
        <c:if test="${requestScope.NOTIFS != null}" var="isNull">
            <c:forEach var="notif" items="${requestScope.NOTIFS}">
                <div class="dropdown-item">${notif}</div>
            </c:forEach>
        </c:if>
        <c:if test="${not isNull}">
            <div class="dropdown-item">No notification yet</div>
        </c:if>
    </div>
</div>  

<!-- JavaScript Libraries -->
<script src="noFilter/lib/jquery/jquery.min.js"></script>
<script src="noFilter/lib/jquery/jquery-migrate.min.js"></script>
<script src="noFilter/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="noFilter/lib/easing/easing.min.js"></script>
<script src="noFilter/lib/mobile-nav/mobile-nav.js"></script>
<script src="noFilter/lib/wow/wow.min.js"></script>
<script src="noFilter/lib/waypoints/waypoints.min.js"></script>
<script src="noFilter/lib/counterup/counterup.min.js"></script>
<script src="noFilter/lib/owlcarousel/owl.carousel.min.js"></script>
<script src="noFilter/lib/isotope/isotope.pkgd.min.js"></script>
<script src="noFilter/lib/lightbox/js/lightbox.min.js"></script>
<!-- Contact Form JavaScript File -->
<script src="noFilter/contactform/contactform.js"></script>

<!-- Template Main Javascript File -->
<script src="noFilter/js/main.js"></script>
