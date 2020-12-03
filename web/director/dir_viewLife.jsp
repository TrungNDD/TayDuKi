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
        <title>TayDuKi - View ${sessionScope.LIFE.idLife}</title>
        <%@include file="/metadata.html" %>
        <script>
            function validate() {
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
        <header id="header" class="bg-white">
            <div class="container">
                <div class="logo float-left">
                    <!-- Uncomment below if you prefer to use an image logo -->
                    <h1 class="text-light"><a href="#" class="scrollto"><span>TayDuKi</span></a></h1>
                    <!-- <a href="#header" class="scrollto"><img src="img/logo.png" alt="" class="img-fluid"></a> -->
                </div>

                <nav class="main-nav float-right d-none d-lg-block">
                    <ul>
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

         <footer id="footer" class="section-bg">
            <div class="footer-top">
                <div class="text-center">
                    <h1>${dto.nameLife}</h1>
                    <font color="red">
                    ${requestScope.SUCCESS}
                    ${requestScope.ERROR}
                    </font>
                </div>

                <div class="container">
                    <div class="form col d-flex justify-content-center p-2" style="font-size: 16px">
                        <form action="updateLife" method="POST" name="form" 
                              onsubmit="return validate()" style="width: 90%; padding-left:26%">
                            IdLife: ${dto.idLife}<br/>
                            Namelife: ${dto.nameLife}
                            <input type="hidden" name="nameLife" value="${dto.nameLife}"/><br/>

                            <div class="form-group">
                                Location: <br/>
                                <input type="text" name="location" value="${dto.location}" required/><br/>
                            </div>
                            
                            <div class="form-group">
                                Description: <br/>
                                <input type="text" name="descLife" value="${dto.descLife}"/><br/>
                            </div>

                            <div class="form-group">
                                No of Shoot: <br/>
                                <input type="number" name="noOfShoot" value="${dto.noOfShoot}" 
                                       required min="0"/>
                                <font color="red" id="noOfShootError">
                                </font><br/>
                            </div>

                            <div class="form-group">
                                Date start: <br/>
                                <input type="date" name="dateStart" value="${dto.dateStart}" required/><br/>
                            </div>

                            <div class="form-group">
                                Date finish: <br/>
                                <input type="date" name="dateFinish" value="${dto.dateFinish}" required/>
                                <font color="red" id="dateError">
                                </font><br/>
                            </div>

                            <input type="submit" name="action" value="Update Life"/>
                            <input type="hidden" name="id" value="${dto.idLife}"/>
                            <input type="hidden" name="idDirector" value="${sessionScope.ID_DIRECTOR}"/>
                        </form>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
