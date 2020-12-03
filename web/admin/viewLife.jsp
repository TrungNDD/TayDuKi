<%-- 
    Document   : viewLife
    Created on : Jul 7, 2020, 9:08:41 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - Manage Life</title>
        <%@include file="/metadata.html" %>
        <script>
            function validate() {
                var name = document.forms["form"]["nameLife"].value;
                if (name.length < 3 || name.length > 30) {
                    document.getElementById("nameLifeError").innerHTML = "Name Life must be between 3 and 30";
                    return false;
                } else {
                    document.getElementById("nameLifeError").innerHTML = "";
                }

                var noOfShoot = document.forms["form"]["noOfShoot"].value;
                if (noOfShoot.match("[e]")) {
                    document.getElementById("noOfShootError").innerHTML = "Provide a valid Integer Number";
                    return false;
                } else {
                    document.getElementById("noOfShootError").innerHTML = "";
                }

                var dateStart = new Date(document.forms["form"]["dateStart"].value);
                var dateFinish = new Date(document.forms["form"]["dateFinish"].value);
                if (dateFinish < dateStart) {
                    document.getElementById("dateError").innerHTML = "Date Finish must be after Date Start";
                    return false;
                } else {
                    document.getElementById("dateError").innerHTML = "";
                }

                return true;
            }
        </script>
    </head>
    <body>
        <c:set var="dto" value="${requestScope.DTO}"/>
        <c:set var="directors" value="${requestScope.directors}"/>
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
                        <li><a href="lifeManagePage">Manage Life</a></li>
                        <li><a href="equipManagePage">Manage Equipment</a></li>
                        <li><a href="logout">Logout</a></li>
                    </ul>
                </nav><!-- .main-nav -->
            </div>
        </header><br/><br/><br/><br/>
        
        <footer id="footer" class="section-bg">
            <div class="footer-top">
                <div class="text-center">
                    <h2>${dto.nameLife}</h2>
                    <font color="red">
                    ${requestScope.SUCCESS}
                    </font>
                </div>
                <div class="container">
                    <div class="form col d-flex justify-content-center p-2"style="font-size: 16px">
                        <form action="updateLife" method="POST" name="form" 
                              onsubmit="return validate()" style="width: 90%; padding-left:26%">
                            IdLife: ${dto.idLife}<br/>

                            <div class="form-group">
                                Namelife: <br>
                                <input type="text" name="nameLife" value="${dto.nameLife}" required/>
                                <font color="red" id="nameLifeError">
                                </font>
                            </div>

                            <c:if test="${sessionScope.ROLE eq 'admin'}">
                                Choose Director: <br/>
                                <select name="idDirector">
                                    <c:forEach var="director" items="${directors}">
                                        <option value="${director.id}" 
                                                selected="<c:if test="${dto.idDirector == director.id}">True</c:if>"">
                                            ${director.id} - ${director.firstName} ${director.lastName}
                                        </option>
                                    </c:forEach>
                                </select><br/>
                            </c:if>

                            <div class="form-group">
                                Location: <br/><input type="text" name="location" value="${dto.location}" required/><br/>
                            </div>

                            Description: <br/><input type="text" name="descLife" value="${dto.descLife}"/><br/>

                            No of Shoot:<br/> <input type="number" name="noOfShoot" value="${dto.noOfShoot}" 
                                                     required min="0"/>
                            <font color="red" id="noOfShootError">
                            </font><br/>

                            Date start:<br/> <input type="date" name="dateStart" value="${dto.dateStart}" required/><br/>

                            Date finish:<br/> <input type="date" name="dateFinish" value="${dto.dateFinish}" required/>
                            <font color="red" id="dateError">
                            </font><br/>

                            <input type="submit" name="action" value="Update Life"/>
                            <input type="hidden" name="id" value="${dto.idLife}"/>
                        </form>
                    </div>
                </div>
        </footer>
    </body>
</html>
