<%-- 
    Document   : equipManage
    Created on : Jul 8, 2020, 11:03:57 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - Manage Equipment</title>
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
                        <li><a>${sessionScope.LIFE.nameLife}</a></li>
                        <li><a href="actorManagePage">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> Manage Actor</a>
                        </li>
                        <li><a href="viewEquipCart">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> View All Equipment in this Life</a>
                        </li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </nav><!-- .main-nav -->
            </div>
        </header><br/><br/><br/><br/>

        <section class="section-bg clearfix" id="pricing">
            <div class="container">
                <h1>Add Equipment to Life</h1>
                <form action="searchEquip" method="POST">
                    <input type="text" name="txtSearch" required="true">
                    <input type="submit" name="action" value="Search">
                </form>
                <font color="red">
                ${requestScope.MESSAGE}
                </font>

                <!--display search result -->
                <c:if test="${requestScope.DTOS != null}">
                    <c:if test="${not empty requestScope.DTOS}" var="isEmpty">
                        <section id="team" class="section-bg">
                            <div class="container">
                                <div class="row">
                                    <c:forEach var="dto" items="${requestScope.DTOS}">
                                        <div class="col-lg-3 col-md-6 wow fadeInUp bg-light" data-wow-delay="0.3s">
                                            <div class="member">
                                                <img src="${dto.imgEquip}" class="img-fluid" alt="${dto.nameEquip}">
                                                <div class="member-info">
                                                    <div class="member-info-content">
                                                        <span>${dto.nameEquip}</span>
                                                        <span>${dto.descEquip}</span>
                                                        <span>Available: ${dto.equipCount}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:if test="${dto.equipCount ne 0}" var="isAvailable">
                                                <form action="addEquipToLife" method="POST" onkeydown="return event.key != 'Enter';">
                                                    <input type="number" name="quantity" max="${dto.equipCount}" min="1"/>
                                                    <input type="submit" value="Add to Life"/>
                                                    <input type="hidden" value="${param.txtSearch}" name="txtSearch"/>
                                                    <input type="hidden" name="idEquip" value="${dto.idEquip}"/>
                                                </form>
                                            </c:if>
                                            <c:if test="${not isAvailable}">
                                                <br>Not available
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </section>
                    </c:if>
                    <c:if test="${not isEmpty}">No result<br/></c:if>
                </c:if>
            </div>
        </section>
    </body>
</html>
