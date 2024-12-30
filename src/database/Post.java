package database;

import java.sql.Date;

public class Post {
    private int id;
    private int userId;
    private User user;
    private String text;
    private Date date;

    public Post(int userId, String text, Date date) {
        this.userId = userId;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int user_id) {
        this.userId = user_id;
    }
    public User getUser() {
        return user;
    }
    public void setUserById(int user_id) {
        this.user = DBConnection.getUserById(user_id);
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return text + "posted on " + date + "\n";
    }
}
