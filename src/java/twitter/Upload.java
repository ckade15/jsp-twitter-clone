/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package twitter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author chris
 */
@MultipartConfig(maxFileSize = 1000000)
public class Upload extends HttpServlet {

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
        
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("delete")){
            try{
               HttpSession session = request.getSession();
                String username = (String)session.getAttribute("username");
                User user = UserModel.getUser(username);
                request.setAttribute("user_id", user.getId());
            
                Connection connection = DatabaseConnection.getConnection();
                String preparedSQL = "update user set image = NULL, filename = NULL "
                    + " where username = ?";
            
                PreparedStatement preparedStatement = connection.prepareStatement(preparedSQL);
            
                preparedStatement.setString(1, user.getUsername());

                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

                response.sendRedirect("Profile"); 
            }catch(SQLException ex){
                System.out.println(ex);
            }
            catch(ClassNotFoundException ex){
                System.out.println(ex);
            }   
        }else{
            // https://www.codejava.net/coding/upload-files-to-database-servlet-jsp-mysql
            InputStream inputStream = null;
            String filename = "";
            Part filePart = request.getPart("filename");
            if (filePart != null) {
                filename = extractFileName(filePart);
                inputStream = filePart.getInputStream();
            }

            try {
                HttpSession session = request.getSession();
                String userName = session.getAttribute("username").toString();

                Connection connection = DatabaseConnection.getConnection();
                String preparedSQL = "update user set image = ?, filename = ? "
                        + " where username = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(preparedSQL);

                preparedStatement.setBlob(1, inputStream);
                preparedStatement.setString(2, filename);
                preparedStatement.setString(3, userName);

                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

                response.sendRedirect("Profile");

            }catch (SQLException exception) { 
                request.setAttribute("error", exception.toString());
                String url = "/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);

            }catch (ClassNotFoundException exception){
                request.setAttribute("error", exception.toString());
                String url = "/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
                System.out.println(exception);
            }
        }
        
    }
    
    // https://www.codejava.net/java-ee/servlet/java-file-upload-example-with-servlet-30-api
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")){
                return s.substring(s.indexOf("=") + 2, s.length() -1);
            }
        }
        return "";
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
