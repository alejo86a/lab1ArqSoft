/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.modelo.JugadorDTO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author docen12
 */
@MultipartConfig(maxFileSize=16177215)
public class InsertarServlet extends HttpServlet {
    
    //Variables de conexion a la BD
    private String dbURL="jdbc:derby://localhost:1527/lab1";
    private String dbUser="root";
    private String dbPass="root";
    //instanciar el objeto JugadorDTO
    JugadorDTO jug= new JugadorDTO();

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
        jug.setFirstName(request.getParameter("firstName"));
        jug.setLastName(request.getParameter("lastName"));
        jug.setEdad(Integer.parseInt(request.getParameter("edad")));
        jug.setPosicion(request.getParameter("posicion"));
        jug.setEquipo(request.getParameter("equipo"));
        jug.setFechaNam(request.getParameter("fechaNacimiento"));
        jug.setNacionalidad(request.getParameter("nacionalidad"));
        
        //Defino el input stream para el archivo que subire
        InputStream inputStream=null;
        
        //Obtengo la parte del archivo a cargar en la peticion (multipart)
        Part filePart=request.getPart("photo");
        //valido que no este vacio el archivo
        if(filePart!=null){
            //imprimo información para debbugin
            System.out.println(filePart.getName());//imprimo el nombre del archivo
            System.out.println(filePart.getSize());//imprimo el tamaño
            System.out.println(filePart.getContentType());//imprimo el MIME
            
            //Obtengo el input stream del archivo cargado
            inputStream=filePart.getInputStream();
        }
        
        //configuro la conexion a la BD con JDBC
        Connection conn = null;//objeto para conectar con la BD
        String message=null; //mensaje de salida
        
        try {
            //conecto a la BD
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn=DriverManager.getConnection(dbURL, dbUser, dbPass);
            
            //construyo el estamento SQL
            String sql  ="INSERT INTO jugador(first_name,last_name,edad,posicion,equipo,fecha_nacimiento,nacionalidad,photo) VALUES(?,?,?,?,?,?,?,?)";
            //Crei el prepareStatement SQL para enlazarlo con el POJO
            PreparedStatement statement=conn.prepareStatement(sql);
            
            statement.setString(1, jug.getFirstName());
            statement.setString(2, jug.getLastName());
            statement.setInt(3, jug.getEdad());
            statement.setString(4, jug.getPosicion());
            statement.setString(5, jug.getEquipo());
            statement.setString(6, jug.getFechaNam());
            statement.setString(7, jug.getNacionalidad());
            if(inputStream!=null){
                statement.setBlob(8, inputStream);
            }
            //enviar el stamento para actualizar la BD  
            int row = statement.executeUpdate();
            if (row>0){
                message = "Archivo cargado y guardado exitosamente";
            }
        } catch (Exception e) {
            message ="ERROR: " +e.getMessage();
            e.printStackTrace();
        }finally{
            if (conn!=null){
                try{
                    conn.close();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
            //coloco elmensaje en el ambito del request
            request.setAttribute("Message", message);
            //reenvio a la vista jsp del mensaje
            getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
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
