<%-- 
    Document   : insertar
    Created on : 9/09/2015, 10:35:21 AM
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
        <title>INSERTAR JUGADOR</title>
    </head>
    <body>
      <center>
   <div class="container well">
        <h1>Album Virtual</h1>
 <form method="post" action="InsertarServlet" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>Nombre: </td>
                    <td><br><input type="text" name="firstName" required="true"  class="form-control" placeholder="First Name"size="50"/></td>
                </tr>
                <tr>
                    <td>Apellido
                    <br><td>
                        <br><input type="text" name="lastName" required="true" class="form-control" placeholder="Last name"size="50"/></td>
                </tr>
              
                 <tr>
                    <td>Edad: </td>
                    <br><td>
                        <br><input type="text" name="edad" required="true" class="form-control" placeholder="Edad"size="50"/></td>
                </tr>
               
                 <tr>
                    <td>Posición: </td>
                    <br><td>
                        <br>
                    <select name="posicion" required="true" class="form-control" placeholder="Posición">
                        <option selected>--Elije una Posición</option>
                        <option value="portero">Portero</option>
                        <option value="def_izquiedo">Defensa Izquierdo</option>
                        <option value="def_central">Defensa Central</option>
                        <option value="def_derecho">Defensa Derecho</option>
                        <option value="lat_izquierdo">Lateral Izquierdo</option>
                        <option value="lat_derecho">Lateral Derecho</option>
                        <option value="med_central">Mediocampista Central</option>
                        <option value="med_ofensivo">Mediocampista Ofensivo</option>
                        <option value="med_creacion">Mediocampista Creación</option>
                        <option value="delantero_centro">Deltantero Centro</option>
                        <option value="delantero_izq">Deltantero Izquierdo</option>
                        <option value="delantero_der">Deltantero Derecho</option>
                    </select></td>
                     </tr>
               <tr>
                    <td>Equipo: </td>
                    <br><td>
         <br><input type="text" name="equipo" required="true" class="form-control" placeholder="Equipo" size="50"/></td>
                </tr>
               
                <tr>
                    <td>Portrait Photo: </td>
                    <td><br><input type="file" name="photo" size="50"/></td>
                </tr>
               
                <tr>
                 <td colspan="2"><br>
                     <input class="btn btn-primary btn-block" type="submit" value="Save">
                 </td>
                </tr>
            </table>
        </form>
        <br>
       </div>
    </center>
    </body>
</html>