/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package twitter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chris
 */
public class GetTweetImage extends HttpServlet {

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
                try{
            
            int id = Integer.parseInt(request.getParameter("filename"));
            
            HttpSession session = request.getSession();
            String username = (String)session.getAttribute("username");
            User user = UserModel.getUser(username);
            int user_id = user.getId();
            
            Connection connection = DatabaseConnection.getConnection();
            String preparedSQL = "select image, filename from tweet where id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(preparedSQL);
            
            preparedStatement.setInt(1, id);
            
            ResultSet result = preparedStatement.executeQuery();
            Blob blob = null;
            
            String filename = "";
            while (result.next()){
                blob = result.getBlob("image");
                filename = result.getString("filename");
                
            }
            
            if (filename.length() == 0){
                
            }else{
                byte[] imageBytes = blob.getBytes(1, (int)blob.length());
            
            
            
                String contentType = this.getServletContext().getMimeType(filename);
            
                response.setHeader("Content-Type", contentType);
            
                OutputStream os = response.getOutputStream();
            
                os.write(imageBytes);
                os.flush();
                os.close();  
            }
            
            
            preparedStatement.close();
            connection.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }catch (ClassNotFoundException ex){
            System.out.println(ex);
        }catch(NullPointerException ex){
            System.out.println(ex);
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
