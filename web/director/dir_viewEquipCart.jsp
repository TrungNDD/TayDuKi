<%-- 
    Document   : dir_viewEquipCart
    Created on : Jul 14, 2020, 11:23:41 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - View Equipment Cart</title>
        <%@include file="/metadata.html" %>
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
                        <li><a>${sessionScope.LIFE.nameLife}</a></li>
                        <li><a href="actorManagePage">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> Manage Actor</a>
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
                <h1>${sessionScope.LIFE.idLife}</h1>
                <font color="red">
                ${requestScope.MESSAGE}
                </font>

                <c:set var="dtos" value="${sessionScope.LIFE.listEquips}"/>
                <c:if test="${dtos != null}" var="isNull">
                    <c:if test="${not empty dtos}" var="isEmpty">
                        <section id="team" class="section-bg">
                            <div class="container">
                                <div class="row">
                                    <c:forEach var="entry" items="${dtos}">
                                        <c:set var="dto" value="${entry.value}"/>
                                        <div class="col-lg-3 col-md-6 wow fadeInUp bg-light" data-wow-delay="0.3s">
                                            <div class="member">
                                                <img src="${dto.imgEquip}" class="img-fluid" alt="${dto.nameEquip}">
                                                <div class="member-info">
                                                    <div class="member-info-content">
                                                        <span>${dto.nameEquip}</span>
                                                        <span>${dto.descEquip}</span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form col d-flex justify-content-center p-2">
                                                <form action="addEquipToLife" method="POST">
                                                    <input type="number" name="quantity" value="${dto.equipCount}"/>
                                                    <input type="hidden" name="idEquip" value="${dto.idEquip}"/>
                                                    <input type="submit" name="action" class="btn btn-info" value="Update"/>
                                                    <a href="deteleEquipFromLife?idEquip=${dto.idEquip}" class="btn btn-danger">Delete</a>
                                                </form>
                                                <!--<form action="deleteEquipFromLife" method="POST">
                                                    <input type="hidden" name="idEquip" value="${dto.idEquip}"/>
                                                    <input type="submit" value="Delete"/>
                                                </form>-->
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
                    No Equipment yet<br/>
                </c:if>
            </div>
        </section>
    </body>
</html>
