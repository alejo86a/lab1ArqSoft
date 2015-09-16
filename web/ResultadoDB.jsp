<%-- 
    Document   : ResultadoDB
    Created on : 16/09/2015, 12:57:30 PM
    Author     : elias.quintero
--%>

<%@page import="java.util.Vector"%>
<%@page import="com.udea.modelo.JugadorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector v=new Vector();
             v=(Vector)request.getAttribute("v");
        for(int i=0;i<=v.size()-1;i++){

            JugadorDTO a1=(JugadorDTO)v.elementAt(i);
           %>
           <h3><%=a1.getFirstName()%> </h3>
        <% }%>
      
    </body>
</html>
