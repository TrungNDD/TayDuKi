<%-- 
    Document   : login
    Created on : Jun 30, 2020, 10:13:50 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <%@include file="metadata.html"%>
    </head>
    <body>
        <%@include file="header.html"%>
        <section id="intro" class="clearfix">
            <div class="container d-flex h-100">
                <div class="row justify-content-center align-self-center">
                    <div class="col-md-6 intro-info order-md-first order-last">
                        <h2>Login</h2>
                            <form action="login" method="POST">
                                <input type="text" name="username" value="${param.username}" placeholder="Username" class="col-6">
                                <input type="password" name="password" value="" placeholder="Password" class="col-6"><br/>                   
                                <input type="submit" name="action" value="Login" class="btn-get-started scrollto"/>
                            </form>
                            <div class="col" style="color: red">
                            ${requestScope.ERROR}
                            </div>
                    </div>

                    <div class="col-md-6 intro-img order-md-last order-first">
                        <img src="noFilter/img/intro-img.svg" alt="a big image here" class="img-fluid">
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
