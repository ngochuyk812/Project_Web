package Model;

import java.sql.Date;

public class ContentComment {
    private String user;
    private int star;
    private int idComment;
    private String content;
    private int type;
    private Date createAt;

    public ContentComment(int idComment, String content, int type) {
        this.idComment = idComment;
        this.content = content;
        this.type = type;
    }

    public ContentComment(String user, int idComment, String content, int type, Date createAt, int star) {
        this.user = user;
        this.idComment = idComment;
        this.content = content;
        this.type = type;
        this.star=star;
        this.createAt = createAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
