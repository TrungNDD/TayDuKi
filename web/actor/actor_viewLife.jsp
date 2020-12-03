<%-- 
    Document   : viewLife
    Created on : Jul 9, 2020, 5:59:48 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Life</title>
        <%@include file="/metadata.html" %>
    </head>
    <body>
        <c:set var="dto" value="${requestScope.DTO}"/>
        <c:set var="life" value="${requestScope.DTO.life}"/>
        <header id="header" class="bg-white">
            <div class="container">
                <div class="logo float-left">
                    <!-- Uncomment below if you prefer to use an image logo -->
                    <h1 class="text-light"><a href="#" class="scrollto"><span>TayDuKi</span></a></h1>
                    <!-- <a href="#header" class="scrollto"><img src="img/logo.png" alt="" class="img-fluid"></a> -->
                </div>

                <nav class="main-nav float-right d-none d-lg-block">
                    <ul>
                        <li><a href="viewLifeByTime?time=all">View All Roles</a></li>
                        <li><a href="viewLifeByTime?time=history">View Life History</a></li>
                        <li><a href="viewLifeByTime?time=upcoming">View Upcoming Life</a></li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </nav><!-- .main-nav -->
            </div>
        </header><br/><br/><br/><br/>

        <footer id="footer" class="section-bg">
            <div class="footer-top">
                <div class="text-center">
                    <h2>${life.nameLife}</h2>
                </div>
                <div class="container">
                    <div class="form col d-flex justify-content-center p-2"style="font-size: 16px">
                        <form action="download" method="POST" target="_blank" style="width: 70%; padding-left:20%">
                            IdLife: ${life.idLife}<br/>

                            Namelife: ${life.nameLife}<br/>

                            Location:<br/> 
                            <input type="text" name="location" value="${life.location}" readonly/><br/>

                            Description:<br/> 
                            <input type="text" name="descLife" value="${life.descLife}" readonly/><br/>

                            No of Shoot:<br/> 
                            <input type="number" name="noOfShoot" value="${life.noOfShoot}" 
                                   readonly min="0"/><br/>

                            Date start:<br/> 
                            <input type="date" name="dateStart" value="${life.dateStart}"  readonly/><br/>

                            Date finish:<br/> 
                            <input type="date" name="dateFinish" value="${life.dateFinish}" readonly/><br/>

                            Role:<br/> 
                            <input name="role" value="${dto.nameRole}" readonly/><br/>
                            Description:<br/> 
                            <input name="description" value="${dto.descRole}" readonly/>

                            <input type="hidden" name="idLife" value="${life.idLife}" readonly/>
                            <input type="hidden" name="nameLife" value="${life.nameLife}" readonly/>
                            <input type="submit" value="Download"/>
                        </form>
                    </div>
                </div>
        </footer>
    </body>
</html>
