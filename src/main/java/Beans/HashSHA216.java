package Beans;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

public class HashSHA216 {

    public static String hash(String pass){
        byte[] encodedBytes = Base64.getEncoder().encode(pass.getBytes());
        return Base64.getEncoder().encodeToString(encodedBytes);
    }
}
