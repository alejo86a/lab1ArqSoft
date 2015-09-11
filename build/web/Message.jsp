<%-- 
    Document   : Message
    Created on : 9/09/2015, 10:36:12 AM
    Author     : camilo.posadaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>
            <h3><%=request.getAttribute("Message")%></h3>
        </center>
        <h1>Hello World!</h1>
    </body>
</html>
