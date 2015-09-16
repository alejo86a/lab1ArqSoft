/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.modelo.JugadorDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author elias.quintero
 */
@MultipartConfig
@WebServlet(name = "ListarDBServlet", urlPatterns = {"/ListarDBServlet"})
public class ListarDBServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
     //Variables de conexion a la BD
    private String dbURL = "jdbc:derby://localhost:1527/lab1";
    private String dbUser = "root";
    private String dbPass = "root";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        ResultSet rs = null;
         Blob blob = null;
        byte[] data = null;
        OutputStream outputStream = null;
         //Obtengo la parte del archivo a cargar en la peticion (multipart)
        Part filePart = request.getPart("photo");
        Connection conn = null;//objeto para conectar con la BD
 
        Vector jugadores = new Vector();
        Vector fotos = new Vector();
        try {
            //conecto a la BD
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            PreparedStatement statement = null;
            
                
            String sql="SELECT * FROM jugador";
            statement = conn.prepareStatement(sql);
            
            
            //enviar el stamento para actualizar la BD  
            rs = statement.executeQuery();

            while (rs.next()) {
                
                JugadorDTO jug= new JugadorDTO();
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
                
                // fullPath para linux --Comentar cuando se este trabajando en windows
                String fullPath = getServletContext().getRealPath("/WEB-INF").replace("/WEB-INF", "/" + nombreFoto); 
                System.out.println(fullPath);
                // fullPath para windows --Comentar cuando se este trabajando en linux
                //String fullPath = getServletContext().getRealPath("\\WEB-INF").replace("\\WEB-INF", "\\" + nombreFoto);                //byte[] archivo=rs.getBytes("photo");
               
                
                FileOutputStream salidaArchivo = new FileOutputStream(fullPath);
                salidaArchivo.write(data);
                salidaArchivo.close();
                
                fotos.addElement(nombreFoto);
                jugadores.addElement(jug);

            }

        } catch (Exception e) {
           
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
            
            request.setAttribute("jugadores",jugadores);
            request.setAttribute("fotos",fotos);
            //reenvio a la vista jsp del mensaje
            getServletContext().getRequestDispatcher("/ResultadoDB.jsp").forward(request, response);
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
