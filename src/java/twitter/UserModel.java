
package twitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserModel {
    
    public static boolean login(User user){
        try{
            Connection connection = DatabaseConnection.getConnection();

            String query = "select id, username, password from user where username = ? ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            
            ResultSet result = statement.executeQuery();
            
            String password = "";
            
            if (result.next()){    
                password = result.getString("password");    
                               
            }
            
            result.close();
            statement.close(); 
            connection.close();
            
            return !password.isEmpty() && user.getPassword().equals(password);        
        }
        catch ( Exception ex ){
            System.out.println(ex);
            return false;
        }        
    }
    
    public static User getUser(String username){
        User user = null;
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "select id, username, password, filename from user where username = ?";
           
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, username);
            ResultSet results = statement.executeQuery();
            
            
            while ( results.next() ){
                int id = results.getInt("id");
                String password = results.getString("password");
                String filename = results.getString("filename");
                
                user = new User(id, username, password, filename);
                

            }
            
            results.close();
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return user;
    }
    
    public static ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            Statement statement = connection.createStatement();
            
            ResultSet results = statement.executeQuery("select * from user");
            
            while ( results.next() ){
                int id = results.getInt("id");
                String username = results.getString("username");
                String password = results.getString("password");
                String filename = results.getString("filename");
                if (filename == null || filename.equals("")){
                    filename = null;
                }
                
                User user = new User(id, username, password, filename);
                
                users.add(user);
            }
            
            results.close();
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
        
        return users;
        
    }
    
    public static boolean addUser(User user){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "insert into user ( username, password ) "
                    + " values ( ?, ? )";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1, why? it's ok to cry
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            
            statement.execute();
            
            statement.close();
            connection.close();
            return true;
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
            return false;
        }
    }
    
    public static void updateUser(User user){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "update user set username = ? , password = ? "
                    + " where id = ? ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }
    
    public static void deleteUser(User user){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "delete from user where id = ? ";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, user.getId());
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }
    public static void deleteProfilePic(String user){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "update user set filename = ?"
                    + " where username = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, null);
            statement.setString(2, user.trim());
            
            statement.execute();
            
            statement.close();
            connection.close();
            
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }
    
    public static void followUser(int followed_by_user_id, int following_user_id){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "insert into following ( followed_by_user_id, following_user_id ) "
                    + " values ( ?, ? )";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, followed_by_user_id);
            statement.setInt(2, following_user_id);
            
            statement.execute();
            
            statement.close();
            connection.close();

            
        }
        catch ( Exception ex ){
            System.out.println(ex);

        }
    }
    public static boolean ensureFollowed(int followed_by_user_id, int following_user_id){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "select * from following where "
                    + " followed_by_user_id = ? and following_user_id = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, followed_by_user_id);
            statement.setInt(2, following_user_id);
            
            ResultSet result = statement.executeQuery();
            String input = "";
            while( result.next() ) {
                input = result.getString("followed_by_user_id");
            }
            
            statement.close();
            connection.close();
            boolean res = !input.isEmpty();

            return res;
        }
        catch ( Exception ex ){
            System.out.println(ex);
            return false;

        }

    }
    
    public static void unfollow(int followed_by_user_id, int following_user_id){
        try{
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "delete from following where followed_by_user_id = ? and following_user_id = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, followed_by_user_id);
            statement.setInt(2, following_user_id);
            
            ResultSet result = statement.executeQuery();
            
            statement.executeUpdate();
            statement.close();
            connection.close();

        }
        catch ( Exception ex ){
            System.out.println(ex);

        }
    }
    
}
