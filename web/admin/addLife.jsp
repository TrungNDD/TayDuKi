<%-- 
    Document   : addLife
    Created on : Jul 6, 2020, 8:46:08 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="/metadata.html" %>
        <script>
            function validate() {
                var idLife = document.forms["form"]["idLife"].value;
                if (!idLife.match("L[0-9]{1,3}")) {
                    document.getElementById("idLifeError").innerHTML = "Invalid Id Life (Lxxx)";
                    return false;
                } else {
                    document.getElementById("idLifeError").innerHTML = "";
                }

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
        <sql:setDataSource var="db" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
                           url="jdbc:sqlserver://localhost:1433;databaseName=TayDuKi_DB"
                           user="sa" password="123456"/>
        <sql:query dataSource="${db}" var="directors">
            Select IdUser, FirstName, LastName From tblUser Where Role = 'director'
        </sql:query>
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
        
        <footer id="footer" class="section-bg">
            <div class="footer-top">
                <div class="text-center">
                    <h2>Add new Life</h2>
                    <font color="red">
                    ${requestScope.SUCCESS}
                    </font>
                </div>
                <div class="container">
                    <div class="form col d-flex justify-content-center p-2"style="font-size: 16px">
                        <form action="addLife" method="POST" name="form" onsubmit="return validate()" style="width: 70%; padding-left:20%">
                            IdLife: <br/>
                            <input type="text" name="idLife" value="${param.idLife}" placeholder="Lxxx" required/><br/>
                            <font color="red" id="idLifeError">
                            ${requestScope.ID_DUPLICATE}
                            </font><br/>
                            Namelife: <br/>
                            <input type="text" name="nameLife" value="${param.nameLife}" required/><br/>
                            <font color="red" id="nameLifeError">
                            </font><br/>

                            Choose Director:  <br/>
                            <select name="idDirector">
                                <c:forEach var="director" items="${pageScope.directors.rows}">
                                    <option value="${director.IdUser}">
                                        ${director.IdUser} - ${director.FirstName} ${director.LastName}
                                    </option>
                                </c:forEach>
                            </select><br/>

                            Location:  <br/>
                            <input type="text" name="location" value="${param.location}" required/><br/>

                            Description:  <br/>
                            <input type="text" name="descLife" value="${param.descLife}"/><br/>

                            No of Shoot  <br/>
                            <input type="number" name="noOfShoot" value="${param.noOfShoot}" 
                                   required min="0"/><br/>
                            <font color="red" id="noOfShootError">
                            </font><br/>

                            Date start:  <br/>
                            <input type="date" name="dateStart" value="${param.dateStart}" required/><br/>

                            Date finish:  <br/>
                            <input type="date" name="dateFinish" value="${param.dateFinish}" required/><br/>
                            <font color="red" id="dateError">
                            </font><br/>

                            <input type="submit" name="action" value="Create new life"/>
                        </form>
                    </div>
                </div>
        </footer>
    </body>
</html>
