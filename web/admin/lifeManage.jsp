<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : lifeManage
    Created on : Jul 6, 2020, 8:36:49 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - Manage Life</title>
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
                        <li><a href="searchUserPage">Manage User</a></li>
                        <li class="active"><a href="addLifePage">Add new Life</a></li>
                        <li><a href="equipManagePage">Manage Equipment</a></li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </nav><!-- .main-nav -->
            </div>
        </header><br/><br/><br/><br/>
        
        <section class="section-bg" id="pricing">
            <div class="container">
                <h1>Life Manager</h1>
                Name life: <form action="searchLife" method="POST">
                    <input type="text" name="txtSearch" required="true">
                    <input type="submit" name="action" value="Search">
                </form><br/>
                <font color="red">
                ${requestScope.MESSAGE}
                </font>

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
                                            <h4 class="card-title"> 
                                                by ${dto.nameDirector}
                                            </h4>
                                            <div>${dto.descLife}</div>
                                            <a href="viewLife?id=${dto.idLife}" class="btn">View</a>
                                            <c:url var="deleteLink" value="deleteLife">
                                                <c:param name="id" value="${dto.idLife}"/>
                                                <c:param name="txtSearch" value="${param.txtSearch}"/>
                                            </c:url>                        
                                            <a href="${deleteLink}" class="btn">Delete</a>
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
    </body>
</html>
