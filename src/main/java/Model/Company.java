package Model;


import java.io.Serializable;

public class Company implements Serializable {
    private int id;
    private String name;
    private int status;
    private String srcImg;

    public Company(int id, String name, String srcImg, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.srcImg = srcImg;
    }
    public Company(int id, String name, String srcImg) {
        this.id = id;
        this.name = name;
        this.srcImg = srcImg;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSrcImg() {
        return srcImg;
    }

    public void setSrcImg(String srcImg) {
        this.srcImg = srcImg;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", srcImg='" + srcImg + '\'' +
                '}';
    }
}
