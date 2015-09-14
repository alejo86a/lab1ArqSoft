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
    <body style="background-color:#E6E6E6">
    <center><h1>Resultado <%=request.getAttribute("Message")%></h1></center>

    <div style="margin: 0 auto;width:800px;height:500px;background-color: #ffffff;border:3px solid #00FFFF;border-radius: 10px 10px 10px 10px;
         -moz-border-radius: 10px 10px 10px 10px;
         -webkit-border-radius: 10px 10px 10px 10px;
         border: 0px solid #000000;">
        <div style="width:100%;height:10%;background-color: #00FFFF;border-radius: 10px 10px 0px 0px;
             -moz-border-radius: 10px 10px 0px 0px;
             -webkit-border-radius: 10px 10px 0px 0px;
             border: 0px solid #000000;"><center><h2><%=request.getAttribute("first_name")%></h2></center></div>
        <div style="float:left;width:35%;height:90%;border:3px solid #00FFFF;border-radius: 0px 0px 10px 10px;
             -moz-border-radius: 0px 0px 10px 10px;
             -webkit-border-radius: 0px 0px 10px 10px;
                 border: 0px solid #000000;">
                   <center>
                       <h3><img src="<%=request.getAttribute("nombreFoto")%>" style="border-radius:50%;height:30;width:40%;margin-top:30%;"/></h3>
        </center>
        </div>
        <div style="float:right;width:65%;height:90%;border:3px solid #00FFFF;border-radius: 0px 0px 10px 10px;
             -moz-border-radius: 0px 0px 10px 10px;
             -webkit-border-radius: 0px 0px 10px 10px;
             border: 0px solid #000000;">
            <center>
                <h3>Nombre: <%=request.getAttribute("first_name")%></h3>
            </center>
            <hr/>
            <center>
                <h3>Apellido: <%=request.getAttribute("last_name")%></h3>
            </center>
            <hr/>
            <center>
                <h3>Edad: <%=request.getAttribute("edad")%></h3>
            </center>
            <hr/>
            <center>
                <h3>Posicion: <%=request.getAttribute("posicion")%></h3>
            </center>
            <hr/>            
            <center>
                <h3>Fecha de nacimiento: <%=request.getAttribute("fecha_nacimiento")%></h3>
            </center>
            <hr/>            
            <center>
                <h3>Nacionalidad : <%=request.getAttribute("nacionalidad")%></h3>
            </center>
            <hr/>
            <center>
                <h3>Equipo: <%=request.getAttribute("equipo")%></h3>
            </center></div>
    </div>

</body>
</html>
