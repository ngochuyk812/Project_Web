package Model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String userName;
    private String passWord;
    private String fullName;
    private String email;
    private String phone;
    private String avatar;
    private String address;
    private int role;
    private int status;
    private int statusLogin;

    public int getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(int statusLogin) {
        this.statusLogin = statusLogin;
    }

    public User(String userName, String passWord, String fullName, String email, String phone, String avatar, String address, int role, int status, int statusLogin) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.address = address;
        this.role = role;
        this.status = status;
        this.statusLogin = statusLogin;
    }

    public User(int id, String userName, String fullName, String email, String phone, String avatar, String address, int role, int status, int statusLogin) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.address = address;
        this.role = role;
        this.status = status;
        this.statusLogin = statusLogin;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public int getStatus() {
        return status;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public String getNameRole() {
        if (this.role == 0) {
            return "Khách hàng";
        } else {
            if (this.role == 1) {
                return "Nhân viên";
            } else {
                if (this.role == 2) {
                    return "Quản lý";
                }
            }
            return "Admin";

        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
