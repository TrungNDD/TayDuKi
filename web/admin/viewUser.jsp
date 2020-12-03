<%-- 
    Document   : viewUser
    Created on : Jul 2, 2020, 9:15:22 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TayDuKi - View User</title>
        <%@include file="/metadata.html" %>
    </head>
    <body>
        <c:set var="dto" value="${requestScope.DTO}"/>
        <header id="header" class="bg-white" style="height: 78px">
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
        <footer id="footer" class="section-bg" style="padding-top: 10px">
            <div class="footer-top">
                <div class="text-center">
                    <h2 class="text-center">${dto.firstName} ${dto.lastName}</h2>
                    <font color="red" class="text-center">
                    ${requestScope.SUCCESS}
                    </font><br/>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-6">
                            <img src="${dto.img}" alt="${dto.firstName}" style="width: 100%"/><br/>
                        </div>

                        <div class="col">
                            <div class="form" style="font-size: 16px">
                                <form action="updateUser" method="POST">
                                    IdUser: ${dto.id} <br/>
                                    First Name: <br/>
                                    <input type="text" name="firstName" value="${dto.firstName}" required/>
                                    <font color="red">
                                    ${requestScope.INVALID.firstNameError}
                                    </font><br/>
                                    Last Name: <br/>
                                    <input type="text" name="lastName" value="${dto.lastName}" required/>
                                    <font color="red">
                                    ${requestScope.INVALID.lastNameError}
                                    </font><br/>

                                    Image: <br/>
                                    <input type="text" name="img" value="${dto.img}" required/>
                                    <font color="red">
                                    ${requestScope.INVALID.imgError}
                                    </font><br/>

                                    Description: <br/>
                                    <input type="text" name="desc" value="${dto.desc}"/><br/>

                                    Phone: <br/>
                                    <input type="text" name="phone" value="${dto.phone}" required/>
                                    <font color="red">
                                    ${requestScope.INVALID.phoneError}
                                    </font><br/>

                                    Email: <br/>
                                    <input type="email" name="email" value="${dto.email}" required/>
                                    <font color="red">
                                    ${requestScope.INVALID.emailError}
                                    </font><br/>

                                    Username: <br/>
                                    <input type="text" name="username" value="${dto.username}" required/>
                                    <font color="red">
                                    ${requestScope.INVALID.usernameError}
                                    </font><br/>

                                    Role: <br/>
                                    <select name="role">
                                        <option value="actor">Actor</option>
                                        <option value="director" 
                                                <c:if test="${dto.role == 'director'}">selected="true"</c:if>>Director</option>
                                        </select>
                                        <br/>
                                        <input type="hidden" name="id" value="${dto.id}"/>
                                    <input type="submit" name="action" value="Update User"/><br/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
