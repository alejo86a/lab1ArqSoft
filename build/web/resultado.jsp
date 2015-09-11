<%-- 
    Document   : resultado
    Created on : 9/09/2015, 11:56:10 AM
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
        <h1>Hello World!</h1>
        
        <center>
            <h3><%=request.getAttribute("Message")%></h3>
        </center>
    
        <center>
            <h3><%=request.getAttribute("first_name")%></h3>
        </center>
        
        <center>
            <h3><%=request.getAttribute("last_name")%></h3>
        </center>
        
        <center>
            <h3><%=request.getAttribute("edad")%></h3>
        </center>
        
        <center>
            <h3><%=request.getAttribute("posicion")%></h3>
        </center>
        
        <center>
            <h3><%=request.getAttribute("equipo")%></h3>
        </center>
        
        <center>
            <h3><img src="<%=request.getAttribute("photo")%>"/></h3>
        </center>
        
       
        
    </body>
</html>
