
package twitter;

import java.sql.Timestamp;

public class Tweet {
    private int id;
    private String text;
    private String timestamp;
    private int user_id;
    private String filename;
    private String author;
    private int like_count;

    
    public Tweet(int id, String text, String timestamp, int user_id, String filename, String author, int like_count) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.user_id = user_id;
        this.filename = filename;
        this.author = author;
        this.like_count = like_count;
    }
    
    

    public Tweet(int id, String text, String timestamp, int user_id, String filename, String author) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.user_id = user_id;
        this.filename = filename;
        this.author = author;
    }
    
    

    public Tweet(int id, String text, int user_id, String filename) {
        this.id = id;
        this.text = text;
        this.user_id = user_id;
        this.filename = filename;
    }

    
    
    public Tweet(int id, String text, String timestamp, int user_id) {
        this(id, text, timestamp, user_id, null);
    }

    public Tweet(int id, String text, String timestamp, int user_id, String filename) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.user_id = user_id;
        this.filename = filename;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }
    
    

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    
    
    
}
