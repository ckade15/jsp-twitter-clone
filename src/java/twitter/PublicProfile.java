/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package twitter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chris
 */
public class PublicProfile extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        User user = UserModel.getUser(username);
        request.setAttribute("main_user", user);
        int id = user.getId();
        User view_user = UserModel.getUser(request.getParameter("username"));
        request.setAttribute("filename",view_user.getFilename());
        request.setAttribute("view_user", view_user);
        request.setAttribute("following", UserModel.ensureFollowed(id, view_user.getId()));
        
        String followed = request.getParameter("followed_by_user_id");
        String following = request.getParameter("following_user_id");
        
        
        
        if(request.getParameter("tweet_id") != null){
            TweetModel.likeTweet(Integer.parseInt(request.getParameter("tweet_id")));
            int following_id = view_user.getId();

            

            ArrayList<Tweet> tweets = TweetModel.getProfileTweets(following_id);
            request.setAttribute("tweets", tweets);

            String url = "/public_profile.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }else if(followed != null){
            int followed_by_user_id = Integer.parseInt(followed);
            int following_user_id = Integer.parseInt(following);
            ArrayList<Tweet> tweets = TweetModel.getProfileTweets(following_user_id);
            request.setAttribute("tweets", TweetModel.getProfileTweets(following_user_id));
            if(UserModel.ensureFollowed(followed_by_user_id, following_user_id)){
                UserModel.unfollow(followed_by_user_id, following_user_id);
                int following_id = view_user.getId();
                request.setAttribute("user_id", id);

                request.setAttribute("following", UserModel.ensureFollowed(id, following_id));

                tweets = TweetModel.getProfileTweets(following_id);
                request.setAttribute("tweets", TweetModel.getProfileTweets(following_id));
                response.setHeader("Refresh", ".1; URL=http://localhost:8080/Twitter/PublicProfile?username="+view_user.getUsername());
                
                
            }else{
                UserModel.followUser(followed_by_user_id, following_user_id);
                int following_id = view_user.getId();

                request.setAttribute("user_id", id);

                request.setAttribute("following", UserModel.ensureFollowed(id, following_id));

                tweets = TweetModel.getProfileTweets(following_id);
                request.setAttribute("tweets", TweetModel.getProfileTweets(following_id));
                response.setHeader("Refresh", ".1; URL=http://localhost:8080/Twitter/PublicProfile?username="+view_user.getUsername());
                
            }
        }
        else if(view_user.getUsername().equalsIgnoreCase(user.getUsername())){
            response.sendRedirect("profile.jsp");
        }
        else{
            int following_id = view_user.getId();
            
            request.setAttribute("user_id", id);

            request.setAttribute("following", UserModel.ensureFollowed(id, following_id));

            ArrayList<Tweet> tweets = TweetModel.getProfileTweets(following_id);
            request.setAttribute("tweets", TweetModel.getProfileTweets(following_id));

            String url = "/public_profile.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
            
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
