<%-- 
    Document   : dir_actorManage
    Created on : Jul 14, 2020, 3:56:23 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - Manage Actor</title>
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
                        <li><a href="viewLife?id=${sessionScope.LIFE.idLife}">${sessionScope.LIFE.nameLife}</a></li>
                        <li><a href="viewActorRole">
                                <i class="fa fa-shopping-cart" aria-hidden="true"></i> 
                                View All Actor and Role in this Life
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
                <h1>Add Actor To Life</h1>
                <form action="SearchUser" method="POST">
                    <input type="text" name="txtSearch" required="true">
                    <input type="submit" name="action" value="Search">
                </form>
                <div>
                    <font color="red">${requestScope.MESSAGE}</font>
                </div>

                <!--display search result -->
                <c:if test="${requestScope.DTOS != null}">
                    <c:if test="${not empty requestScope.DTOS}" var="isEmpty">
                        <section id="team" class="section-bg">
                            <div class="container">
                                <div class="row">
                                    <c:forEach var="dto" items="${requestScope.DTOS}">
                                        <div class="col-lg-3 col-md-6 wow fadeInUp bg-white" data-wow-delay="0.3s">
                                            <div class="member" style="margin-bottom: 0">
                                                <img src="${dto.img}" class="img-fluid" alt="${dto.username}">
                                                <div class="member-info">
                                                    <div class="member-info-content">
                                                        <span>${dto.firstName} ${dto.lastName} - ${dto.role}</span>
                                                        <span>${dto.desc}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form col d-flex justify-content-center p-2">
                                                <form action="addActorToLife" method="POST">
                                                    Role: <br/>
                                                    <input type="text" name="nameRole" 
                                    value="<c:if test="${requestScope.idActor == dto.id}">${param.nameRole}</c:if>" 
                                                           required/><br>

                                                    Description: <br/>
                                                    <input type="text" name="descRole" 
                                    value="<c:if test="${requestScope.idActor == dto.id}">${param.descRole}</c:if>"/><br>

                                                    <input type="hidden" name="idActor" value="${dto.id}"/><br>
                                                    <input type="submit" value="Add" class="btn btn-primary btn-block"/>
                                                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
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
                <br>
            </div>
        </section>
    </body>
</html>
