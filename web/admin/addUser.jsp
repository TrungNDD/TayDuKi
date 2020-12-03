<%-- 
    Document   : addUser
    Created on : Jun 30, 2020, 12:28:44 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - Add User</title>
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
                    <h2>Add new User</h2>
                    <font color="red">
                    ${requestScope.SUCCESS}
                    </font>
                </div>
                <div class="container">
                    <div class="form col d-flex justify-content-center p-2"style="font-size: 16px">
                        <form action="addUser" method="POST" style="width: 70%; padding-left:20%">
                            IdUser: <br/>
                            <input type="text" name="idUser" value="${param.idUser}"/><br/>
                            <font color="red">
                            ${requestScope.INVALID.idError}
                            </font><br/>
                            
                            First Name:  <br/>
                            <input type="text" name="firstName" value="${param.firstName}"/><br/>
                            <font color="red">
                            ${requestScope.INVALID.firstNameError}
                            </font><br/>
                            
                            Last Name:  <br/>
                            <input type="text" name="lastName" value="${param.lastName}"/><br/>
                            <font color="red">
                            ${requestScope.INVALID.lastNameError}
                            </font><br/>
                            
                            Image:  <br/>
                            <input type="text" name="imgUser" value="${param.imgUser}"/><br/>
                            <font color="red">
                            ${requestScope.INVALID.imgError}
                            </font><br/>
                            
                            Description:  <br/>
                            <input type="text" name="descUser" value="${param.descUser}"/><br/>
                            
                            Phone:  <br/>
                            <input type="text" name="phone" value="${param.phone}"/><br/>
                            <font color="red">
                            ${requestScope.INVALID.phoneError}
                            </font><br/>
                            
                            Email:  <br/>
                            <input type="email" name="email" value="${param.email}"/><br/>
                            <font color="red">
                            ${requestScope.INVALID.emailError}
                            </font><br/>
                            
                            Username:  <br/>
                            <input type="text" name="username" value="${param.username}"/><br/>
                            <font color="red">
                            ${requestScope.INVALID.usernameError}
                            </font><br/>
                            
                            Password:  <br/>
                            <input type="password" name="password"/><br/>
                            <font color="red">
                            ${requestScope.INVALID.passwordError}
                            </font><br/>
                            
                            Confirm Password:  <br/>
                            <input type="password" name="confirm"/><br/>
                            <font color="red">
                            ${requestScope.INVALID.confirmError}
                            </font><br/>
                            
                            Role: <select name="role">
                                <option value="actor">Actor</option>
                                <option value="director" 
                                        <c:if test="${param.role == 'director'}">selected="true"</c:if>>Director
                                </option>
                            </select><br/>
                            <input type="submit" name="action" value="Create new user"/>
                        </form>
                    </div>
                </div>
        </footer>
    </body>
</html>
