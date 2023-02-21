package Model;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VerifyRecaptcha {
    private static final String url = "https://www.google.com/recaptcha/api/siteverify";
    private static final String secret = "6LfUDp8kAAAAABBOSfSZGzUS4Fue9QBovtWwP32y";
    private static final String USER_AGENT = "Mozilla/5.0";

    public static boolean verify(String captcha) throws IOException {
        if(captcha == null || captcha.equals(""))
            return false;
        URL urlReq = new URL(url);
        HttpsURLConnection req = (HttpsURLConnection) urlReq.openConnection();
        req.setRequestMethod("POST");
        req.setRequestProperty("User-Agent", USER_AGENT);
        req.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        String data = "secret=" + secret + "&response="+captcha;
        req.setDoOutput(true);


        DataOutputStream wr = new DataOutputStream(req.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        int resCode = req.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        if(response.toString().contains("true"))
            return  true;
        else
            return false;

    }

    public static void main(String[] args) throws IOException {
        verify("03AFY_a8URxROupLzvrAGxvX4mr6Wi4gwQxTZyNShAMBAUmFTqL97wnVLg9G-fPMV_6yZMZDN9m2t4MUrwlQFy-JKWRKGqFeTNiOKMeZqTxz07mMfT8gY5Nsn1QVs4rlhqVv0VGb2A2FPor8weNJCzQOk1QMRS9ZNeNDirIWUtIERZpkWeGX0I05_qh3QvqcqMPVl9rzCyXhoHi_kLiGoDMMjt__jvQXcY6N-4P2od2J-8rxch7BBpxEZvwX5_5GKvAy4D_wxFY-IAWPj0zBxUWPi9FVYGs_tPlq-RF0tqqYSrVOC65R72yfDZVSk-wE3OKyGCjxLUDbakKcIIY-5Tf7qIRgfOaZhEgJBaGuQn-sh8ELbkOvqGKWu1-XNWqI852CUthdilrlSbZXiYnZiBJ_FFWVQDaXHy_zYSRr2-jIzuug9Yw8pUExSzUQW2LGyoYiwSMla5h837Fu5hJETG9s-bYyFvx_OXWtRN84r4aJqiOFeDfc2FntdLr6CAEBJKgd1ZVxeqI1JurUst5o6nfPEPVxm52IgI0U9fMle0aDTb7lLb_i1II-5UexUHDab5FcPYnHZ7KEF8qSlmCS1yQFbcaSzVIJH68Q");
    }
}
