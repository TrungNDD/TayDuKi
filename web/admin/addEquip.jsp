<%-- 
    Document   : addEquip
    Created on : Jul 8, 2020, 11:11:53 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Equipment</title>
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
                    <h2>New Equipment</h2>
                    <font color="red">
                    ${requestScope.SUCCESS}
                    </font>
                </div>
                <div class="container">
                    <div class="form col d-flex justify-content-center p-2"style="font-size: 16px">
                        <form action="addEquip" method="POST" style="width: 70%; padding-left:20%">
                            Id Equipment: <br/>
                            <input type="text" name="idEquip" value="${param.idEquip}" placeholder="Exxx" required/>
                            <font color="red">
                            ${requestScope.INVALID}
                            </font><br/>

                            Name Equipment: <br/>
                            <input type="text" name="nameEquip" value="${param.nameEquip}" required/><br/>

                            Equipment Description: <br/>
                            <input type="text" name="descEquip" value="${param.descEquip}"/><br/>

                            Image Equipment: <br/>
                            <input type="text" name="imageEquip" value="${param.imageEquip}" required/><br/>

                            Number of Equipment: <br/>
                            <input type="number" name="equipCount" value="${param.equipCount}" required/><br/>

                            <label for="available">Available</label>
                            <input type="checkbox" name="available" id="available" style="width: 10%"
                                   <c:if test="${param.available eq 'true'}">checked</c:if> value="true"/>
                            <br/>
                            <input type="submit" name="action" value="Add new Equipment"/>
                        </form>
                    </div>
                </div>
        </footer>
    </body>
</html>
