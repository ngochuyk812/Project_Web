package Model;

import java.util.Date;

public class Comment {
    private String username;
    private Date time;
    private float star;
    private String comment;
    private int idpost;

    public Comment(String username, Date time, float star, String comment, int idpost) {
        this.username = username;
        this.time = time;
        this.star = star;
        this.comment = comment;
        this.idpost = idpost;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getStart() {
        return star;
    }

    public void setStart(float star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", username='" + username + '\'' +
                ", time=" + time +
                ", star=" + star +
                ", comment='" + comment + '\'' +
                ", idpost=" + idpost +
                '}';
    }
}
