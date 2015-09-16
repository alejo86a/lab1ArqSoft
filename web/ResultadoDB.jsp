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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="style.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1 class="text-center">Listado De base de datos</h1>
        <table class="table table-bordered table-striped">
            <thead>
        <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Edad</th>
            <th>Posicion</th>
            <th>Equipo</th>
            <th>Fecha De nacimiento</th>
        </tr>
    </thead>
        <% Vector v=new Vector();
             v=(Vector)request.getAttribute("jugadores");
        for(int i=0;i<=v.size()-1;i++){

            JugadorDTO jugador=(JugadorDTO)v.elementAt(i);
           %>
           
           
          <tr>
              <td><%=i+1%></td>
              <td><%=jugador.getFirstName()%></td>
              <td><%=jugador.getLastName()%></td>
              <td><%=jugador.getEdad()%></td>
              <td><%=jugador.getPosicion()%></td>
              <td><%=jugador.getEquipo() %></td>
               <td><%=jugador.getFechaNam()%></td>
           
        </tr>
           
        <% }%>
        
        </table>
      
    </body>
</html>
