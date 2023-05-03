package Model;

import Security.Authorizeds;

import java.util.ArrayList;

public class Role {
    private int id;
    private String name;
    private ArrayList<Integer> arrPermission ;
    private int status;

    public Role(String name, int status) {
        this.name = name;
        this.status = status;
        arrPermission = new ArrayList<>();
    }
    public Role(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
        arrPermission = new ArrayList<>();
    }
    public Role(int id, String name, int status, ArrayList<Integer> arrPermission) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.arrPermission = arrPermission;
    }

    public Role() {
        arrPermission = new ArrayList<>();

    }

    public Role(int i) {
        this.id = i;
    }

    public void addPermission(int i){
        this.arrPermission.add(i);
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

    public ArrayList<Integer> getArrPermission() {
        return arrPermission;
    }

    public void setArrPermission(ArrayList<Integer> arrPermission) {
        this.arrPermission = arrPermission;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String rs =  "Role: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", arrPermission="  ;
        for (int tmp: arrPermission) {
            rs+= tmp + ",";
        }
        return rs;
    }
}
