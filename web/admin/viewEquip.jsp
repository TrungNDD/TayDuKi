<%-- 
    Document   : viewEquip
    Created on : Jul 8, 2020, 1:43:41 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - View Equipment</title>
        <%@include file="/metadata.html"%>
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
                <h2 class="text-center">${dto.nameEquip}</h2>
                <div class="container">
                    <div class="row">
                        <div class="col-6">
                            <image src="${dto.imgEquip}" alt="${dto.nameEquip}" style="width: 100%"/><br/>
                        </div>

                        <div class="col">
                            <div class="form" style="font-size: 16px">
                                <%--my form goes here --%>
                                <font color="red">${requestScope.SUCCESS}</font><br/>
                                <form action="updateEquip" method="POST">
                                    IdEquip: ${dto.idEquip}<br/>

                                    <div class="form-group">
                                        Name Equipment:<br/>
                                        <input type="text" name="nameEquip" value="${dto.nameEquip}" required/><br/>
                                    </div>
                                    <div class="form-group">
                                        Description: <br/>
                                        <input type="text" name="descEquip" value="${dto.descEquip}"/><br/>
                                    </div>

                                    <div class="form-group">
                                        No of Equipments: <br/>
                                        <input type="number" name="equipCount" value="${dto.equipCount}" 
                                               required min="0"/><br/>
                                    </div>

                                    <label for="available">Available</label>
                                    <input type="checkbox" name="available" id="available" style="width: 10%"
                                           <c:if test="${dto.available}">checked</c:if> value="true"/>
                                           <br/>

                                           <div>
                                               <input type="submit" name="action" value="Update Equip"/>
                                           </div>
                                           <input type="hidden" name="idEquip" value="${dto.idEquip}"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
