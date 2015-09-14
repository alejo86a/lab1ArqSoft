<%-- 
    Document   : buscar
    Created on : 9/09/2015, 11:03:54 AM
    Author     : camilo.posadaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="style.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <title>BUSCAR JUGADOR</title>
    </head>
    <body>
    <center>
        <div class="container well">
            <h1>Busqueda</h1>
            <h2><%
                String mensaje;
                if(request.getAttribute("Message")==null){
                    mensaje="Ingrese su busqueda";
                }else{
                    mensaje=(String)request.getAttribute("Message");
                }%><%=mensaje%></h2>

            <form method="post" action="BuscarServlet" enctype="multipart/form-data">
                <table border="0">
                    <tr>
                        <td>Nombre: </td>
                        <td><br><input type="text" name="nombre" class="form-control" placeholder="First Name"size="50"/></td>
                    </tr>

                    <tr>
                        <td>Apellido:</td>
                        <br><td>
                            <br><input type="text" name="lastName" class="form-control" placeholder="Last name"size="50"/></td>
                    </tr>

                    <tr>
                        <td colspan="2"><br>
                            <input class="btn btn-primary btn-block" type="submit" value="Buscar">
                        </td>
                    </tr>
                </table>
            </form>
            <br>
        </div>
    </center>
</body>
</html>
