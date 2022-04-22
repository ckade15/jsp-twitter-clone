/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twitter;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author chris
 */
public class TweetModel {
    public static void likeTweet(int id){
        try{
            Connection connection = DatabaseConnection.getConnection();

            String query = "UPDATE tweet set like_count = like_count + 1 WHERE id = ? ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            
            statement.setInt(1, id);
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    public static void deleteTweet(int id){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "delete from tweet where id = ? ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            
            statement.setInt(1, id);
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }
    public static ArrayList<Tweet> getProfileTweets(int user_id){
        ArrayList<Tweet> tweets = new ArrayList();
        
        try{
            Connection connection = DatabaseConnection.getConnection();
            String query = "select * from tweet where user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user_id);
            
            
            ResultSet results = statement.executeQuery();
            
            while(results.next()){
                int tweet_id = results.getInt("id");
                String text = results.getString("text");
                int userId = results.getInt("user_id");
                Timestamp timestamp = results.getTimestamp("timestamp");
                String filename = results.getString("filename");
                int like_count = results.getInt("like_count");
                if (filename == null || filename.equals("")){
                    filename = null;
                }
                User creator = UserModel.getUser(userId);
                String s = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss").format(timestamp);
                
                Tweet tweet = new Tweet(tweet_id, text, s, userId,  filename, creator.getUsername(), like_count);
                tweets.add(tweet);
            }
                
            statement.close();
            results.close();
            connection.close();
            
            
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex);
        }
        
        return tweets;
    }
    
    public static ArrayList<Tweet> getHomeTweets(int user_id){
        ArrayList<Tweet> homeTweets = new ArrayList<>();
       
        for (Follower follower : UserModel.getFollowing(user_id)){
            try{
                
                int follow = follower.getFollowing_user_id();
                if (follow == user_id){
                    
                }else{
                    ArrayList<Tweet> twt = TweetModel.getProfileTweets(follow);
                    homeTweets.addAll(twt);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
        homeTweets.addAll(TweetModel.getProfileTweets(user_id));
            
        
            
        
        return homeTweets;
            
    }
    
    // https://www.codejava.net/java-ee/servlet/java-file-upload-example-with-servlet-30-api
    private static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")){
                return s.substring(s.indexOf("=") + 2, s.length() -1);
            }
        }
        return "";
    }
}

