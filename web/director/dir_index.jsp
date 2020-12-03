<%-- 
    Document   : indexDirector
    Created on : Jun 30, 2020, 12:16:09 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - Director</title>
        <%@include file="/metadata.html"%>
    </head>
    <body>
        <header id="header" class="bg-white">
            <div class="container">
                <div class="logo float-left">
                    <!-- Uncomment below if you prefer to use an image logo -->
                    <h1 class="text-light"><a href="#" class="scrollto"><span>TayDuKi</span></a></h1>
                    <!-- <a href="#header" class="scrollto"><img src="img/logo.png" alt="" class="img-fluid"></a> -->
                </div>

                <nav class="main-nav float-right d-none d-lg-block">
                    <ul>
                        <li><a href="index">Manage Life</a></li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </nav><!-- .main-nav -->
            </div>
        </header><br/><br/><br/><br/>

        <section class="section-bg clearfix" id="pricing">
            <div class="container">
                <h1>Hello Director!</h1>
                Name life: <form action="searchLife" method="POST">
                    <input type="text" name="txtSearch" required="true">
                    <input type="hidden" name="idDirector" value="${sessionScope.ID_DIRECTOR}"/>
                    <input type="submit" name="action" value="Search">
                </form>

                <!--display search result -->
                <c:if test="${requestScope.DTOS != null}">
                    <c:if test="${not empty requestScope.DTOS}" var="isEmpty">
                        <div class="row flex-items-xs-middle flex-items-xs-center">
                            <c:forEach var="dto" items="${requestScope.DTOS}">
                                <div class="col-xs-12 col-lg-4">
                                    <div class="card">
                                        <div class="card-header">
                                            <h3>${dto.nameLife}</h3>
                                            <h2>${dto.idLife}</h2>
                                        </div>
                                        <div class="card-block">
                                            <div>${dto.descLife}</div>
                                            <a href="viewLife?id=${dto.idLife}" class="btn">View</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${not isEmpty}">No result<br/></c:if>
                </c:if>
            </div>
        </section>

        <%@include file="/notif.jsp"%>
    </body>
</html>
