<%-- 
    Document   : dir_viewActorRole
    Created on : Jul 14, 2020, 4:43:30 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - View Roles</title>
        <%@include file="/metadata.html"%>
    </head>
    <body>
        <c:set var="dtos" value="${requestScope.DTOS}"/>
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
                        <li><a href="viewLife?id=${sessionScope.LIFE.idLife}">${sessionScope.LIFE.nameLife}</a></li>
                        <li><a href="actorManagePage">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> Manage Actor</a>
                            </a>
                        </li>
                        <li><a href="equipManagePage">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> Manage Equipment</a>
                        </li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </nav><!-- .main-nav -->
            </div>
        </header><br/><br/><br/><br/>

        <section class="section-bg clearfix" id="pricing">
            <div class="container">
                <h1>${sessionScope.LIFE.nameLife}</h1>
                <font color="red">
                ${requestScope.MESSAGE}
                </font><br>

                <c:if test="${dtos != null}" var="isNull">
                    <c:if test="${not empty dtos}" var="isEmpty">

                        <section id="team" class="section-bg">
                            <div class="container">
                                <div class="row">
                                    <c:forEach var="dto" items="${dtos}">
                                        <div class="col-lg-3 col-md-6 wow fadeInUp bg-white" data-wow-delay="0.3s">
                                            <div class="member" style="margin-bottom: 0">
                                                <img src="${dto.actor.img}" class="img-fluid" alt="${dto.actor.username}">
                                                <div class="member-info">
                                                    <div class="member-info-content">
                                                        <span>${dto.actor.firstName} ${dto.actor.lastName}</span><br>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form col d-flex justify-content-center p-2">
                                                <form action="updateActorRole" method="POST">
                                                    Role: <br/>
                                                    <input type="text" name="nameRole" value="${dto.nameRole}" required/>
                                                    Description: <br/>
                                                    <input type="text" name="descRole" value="${dto.descRole}"/>

                                                    <input type="hidden" name="idActor" value="${dto.idActor}"/>
                                                    <input type="submit" class="btn btn-link" value="Update"/>
                                                    <a href="deleteActorRole?idActor=${dto.idActor}" class="btn btn-link">Delete</a>
                                                </form>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </section>
                    </c:if>
                    <c:if test="${not isEmpty}">No result<br/></c:if>
                </c:if>
                <c:if test="${not isNull}">
                    No Actor Role yet<br/>
                </c:if>
            </div>
        </section>
    </body>
</html>
