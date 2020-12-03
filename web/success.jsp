<%-- 
    Document   : success
    Created on : Jun 30, 2020, 1:34:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${requestScope.SUCCESS}</h1>
        <a href="${requestScope.PREVIOUS}">Back</a>
    </body>
</html>
