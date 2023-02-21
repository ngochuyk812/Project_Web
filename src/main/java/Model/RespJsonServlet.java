package Model;

import com.google.gson.Gson;

public class RespJsonServlet {
    private String message;

    public RespJsonServlet(String message) {
        this.message = message;
    }
    public String json(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }

}
