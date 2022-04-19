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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author chris
 */
@MultipartConfig(maxFileSize = 1000000)
public class CreateTweet extends HttpServlet {

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
        if (!Login.ensureUserIsLoggedIn(request)){
            request.setAttribute("message", "You must login to view profile page.");
        }
        
        String action = request.getParameter("action");
        if (action == null){
            String url = "/create_tweet.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
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
        
        InputStream inputStream = null;
            String filename = "";
            Part filePart = request.getPart("filename");
            if (filePart != null) {
                filename = extractFileName(filePart);
                inputStream = filePart.getInputStream();
            }

            try {
                HttpSession session = request.getSession();
                String username = (String)session.getAttribute("username");
                User user = UserModel.getUser(username);
                int user_id = user.getId();
                
                Connection connection = DatabaseConnection.getConnection();
                String preparedSQL = "insert into tweet (id, text, user_id, image, filename) "
                        + " values (?, ?, ?, ?, ?)";
                
                

                PreparedStatement preparedStatement = connection.prepareStatement(preparedSQL);
                
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, request.getParameter("text"));
                
                preparedStatement.setInt(3, user_id);
                preparedStatement.setBlob(4, inputStream);
                preparedStatement.setString(5, filename);

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

/*
else if(action.equalsIgnoreCase("createTweetWithImage")){
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
                String preparedSQL = "insert into tweet (id, text, user_id, image, filename) "
                        + " values (?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(preparedSQL);
                
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, request.getParameter("text"));
                preparedStatement.setInt(3, Integer.parseInt(request.getParameter("user_id")));
                preparedStatement.setBlob(4, inputStream);
                preparedStatement.setString(5, filename);

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
            
*/