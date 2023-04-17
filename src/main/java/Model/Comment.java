package Model;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private int id;
    private int idProduct;
    private int idUser;
    private String avatar;
    private String userName;
    private List<String> listImg;
    private List<String> listVideo;
    private String content;
    private int star;
    private int status;
    private Date createAt;

    public Comment(int idProduct, int idUser, int star, int status) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.star = star;
        this.status = status;
    }

    public Comment(int id, String content, int idProduct, String userName, List<String> listImg, List<String> listVideo, int star, int status, Date createAt, String avatar) {
        this.id = id;
        this.avatar = avatar;
        this.content = content;
        this.idProduct = idProduct;
        this.userName = userName;
        this.listImg = new ArrayList<>();
        this.listVideo = new ArrayList<>();
        this.star = star;
        this.status = status;
        this.createAt = createAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Comment() {
        this.listImg = new ArrayList<>();
        this.listVideo = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getListImg() {
        return listImg;
    }

    public void setListImg(List<String> listImg) {
        this.listImg = listImg;
    }

    public List<String> getListVideo() {
        return listVideo;
    }

    public void setListVideo(List<String> listVideo) {
        this.listVideo = listVideo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
