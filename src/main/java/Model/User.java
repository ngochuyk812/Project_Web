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
    private Role role;
    private int status;
    private int statusLogin;

    public int getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(int statusLogin) {
        this.statusLogin = statusLogin;
    }

    public User(String userName, String passWord, String fullName, String email, String phone, String avatar, String address, Role role, int status, int statusLogin) {
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

    public User(int id, String userName, String fullName, String email, String phone, String avatar, String address, Role role, int status, int statusLogin) {
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

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public int getStatus() {
        return status;
    }

    public void setRole(Role role) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameRole() {
        System.out.println(role.getId());
        if(role.getId()==3){
            return "Admin";
        }else {
            if(role.getId()==4){
                System.out.println(12);
                return "User";

            }
        }
        System.out.println(123);
        return "Nhân viên";
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", statusLogin=" + statusLogin +
                '}';
    }
}
