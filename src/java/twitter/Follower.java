/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twitter;

/**
 *
 * @author chris
 */
public class Follower {
    private int id;
    private int followed_by_user_id;
    private int following_user_id;

    public Follower(int id, int followed_by_user_id, int following_user_id) {
        this.id = id;
        this.followed_by_user_id = followed_by_user_id;
        this.following_user_id = following_user_id;
    }

    public Follower(int followed_by_user_id, int following_user_id) {
        this.followed_by_user_id = followed_by_user_id;
        this.following_user_id = following_user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowed_by_user_id() {
        return followed_by_user_id;
    }

    public void setFollowed_by_user_id(int followed_by_user_id) {
        this.followed_by_user_id = followed_by_user_id;
    }

    public int getFollowing_user_id() {
        return following_user_id;
    }

    public void setFollowing_user_id(int following_user_id) {
        this.following_user_id = following_user_id;
    }
    
    
}
