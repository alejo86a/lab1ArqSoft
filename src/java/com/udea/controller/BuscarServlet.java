/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import com.udea.modelo.JugadorDTO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.derby.client.am.ByteArrayCombinerStream;

/**
 *
 * @author docen12
 */
@MultipartConfig(maxFileSize = 16177215)
public class BuscarServlet extends HttpServlet {

    //Variables de conexion a la BD
    private String dbURL = "jdbc:derby://localhost:1527/lab1";
    private String dbUser = "root";
    private String dbPass = "root";
    //instanciar el objeto JugadorDTO
    JugadorDTO jug = new JugadorDTO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("lastName");
        System.out.println(nombre);
        System.out.println(nombre.length());
        System.out.println(apellido.length());
        Blob blob = null;
        byte[] data = null;
        ResultSet rs = null;
        int entro=0;
        //Defino el input stream para el archivo que subire
        OutputStream outputStream = null;
        //InputStream inputStream=null;

        //Obtengo la parte del archivo a cargar en la peticion (multipart)
        Part filePart = request.getPart("photo");

        //configuro la conexion a la BD con JDBC
        Connection conn = null;//objeto para conectar con la BD
        String message = null; //mensaje de salida

        try {
            //conecto a la BD
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            PreparedStatement statement = null;


            if (nombre.length()>=1&& apellido.length()>=1) {
                //construyo el estamento SQL
                String sql = "SELECT  first_name,last_name,edad,posicion,equipo,fecha_nacimiento,nacionalidad,photo FROM jugador WHERE first_name=? AND last_name=?";
                //Crei el prepareStatement SQL para enlazarlo con el POJO
                statement = conn.prepareStatement(sql);

                statement.setString(1, nombre);
                statement.setString(2, apellido);
                System.out.println("entra al if nombre y apellidos no nulos");
                
            } else if (apellido.length()>=1) {
                //construyo el estamento SQL
                String sql = "SELECT  first_name,last_name,edad,posicion,equipo,fecha_nacimiento,nacionalidad,photo FROM jugador WHERE last_name=?";
                //Crei el prepareStatement SQL para enlazarlo con el POJO
                statement = conn.prepareStatement(sql);

                statement.setString(1, apellido);
                System.out.println("Nombre nulo");
                
            } else if(nombre.length()>=1) {
                //construyo el estamento SQL
                String sql = "SELECT  first_name,last_name,edad,posicion,equipo,fecha_nacimiento,nacionalidad,photo FROM jugador WHERE first_name=?";
                //Crei el prepareStatement SQL para enlazarlo con el POJO
                statement = conn.prepareStatement(sql);
                statement.setString(1, nombre);
                System.out.println("Apellido Nulo");
            }else{
                System.out.println("Entro Al Else");
              String mensaje="No ha ingresado ningun campo";
              request.setAttribute("Message", mensaje);
              getServletContext().getRequestDispatcher("/buscar.jsp").forward(request, response);
            }

            //enviar el stamento para actualizar la BD  
            rs = statement.executeQuery();

            while (rs.next()) {
                entro=1;
                jug.setFirstName(rs.getString("first_name"));
                jug.setLastName(rs.getString("last_name"));
                jug.setEdad(rs.getInt("edad"));
                jug.setPosicion(rs.getString("posicion"));
                jug.setEquipo(rs.getString("equipo"));
                jug.setFechaNam(rs.getString("fecha_nacimiento"));
                jug.setNacionalidad(rs.getString("nacionalidad"));

                blob = rs.getBlob("photo");
                data = blob.getBytes(1, (int) blob.length());

                String nombreFoto = "foto" + ((int) (Math.random() * 100000)) + ".jpg";
                request.setAttribute("nombreFoto", nombreFoto);
                String fullPath = getServletContext().getRealPath("\\WEB-INF").replace("\\WEB-INF", "\\" + nombreFoto);                //byte[] archivo=rs.getBytes("photo");
                FileOutputStream salidaArchivo = new FileOutputStream(fullPath);
                salidaArchivo.write(data);
                salidaArchivo.close();
                message = "";

            }
            if (entro == 0){
                String mensaje="Jugador no encontrado";
                request.setAttribute("Message", mensaje);
                getServletContext().getRequestDispatcher("/buscar.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            //coloco elmensaje en el ambito del request
            request.setAttribute("first_name", jug.getFirstName());
            request.setAttribute("last_name", jug.getLastName());
            request.setAttribute("edad", jug.getEdad());
            request.setAttribute("posicion", jug.getPosicion());
            request.setAttribute("equipo", jug.getEquipo());
            request.setAttribute("fecha_nacimiento", jug.getFechaNam());
            request.setAttribute("nacionalidad", jug.getNacionalidad());
            request.setAttribute("Message", message);
            
            //reenvio a la vista jsp del mensaje
            getServletContext().getRequestDispatcher("/resultado.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
