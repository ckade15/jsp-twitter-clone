/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class FollowerModel {
    public static ArrayList<Follower> getFollowing(int id){
        ArrayList<Follower> following = new ArrayList<>();
        
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "select * from following where followed_by_user_id = ?";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, id);
            
            ResultSet result = statement.executeQuery();
            
            while ( result.next() ){
                int followed_by_user_id = result.getInt("followed_by_user_id");
                int following_user_id = result.getInt("following_user_id");
                Follower follower = new Follower(followed_by_user_id, following_user_id);
                following.add(follower);
            }
            
            connection.close();
            result.close();
            statement.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        return following; 
    }
}
