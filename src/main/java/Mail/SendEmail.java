package Mail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Session;
import java.util.Properties;
import java.util.Random;

public class SendEmail {
    public static String sendCodeMail(String toEmail) {
        Random rd = new Random();
        String code = rd.nextInt(9) + "" + rd.nextInt(9) + "" + rd.nextInt(9) + "" + rd.nextInt(9) + "" + rd.nextInt(9) + "" + rd.nextInt(9) + "" + rd.nextInt(9) + "";

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("nguyenhauxmvt@gmail.com");
        mailSender.setPassword("zadhllwgaiyurjyn");
        System.out.println(code);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("session.setDebug", "true");
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Code");
            message.setText(code);
            mailSender.send(message);
            return code;
        } catch (Exception e) {
            return null;
        }
    }
    public static void sendPass(String toEmail,String pass) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("nguyenhauxmvt@gmail.com");
        mailSender.setPassword("zadhllwgaiyurjyn");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("session.setDebug", "true");
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Code");
            message.setText(pass);
            mailSender.send(message);
        } catch (Exception e) {
        }
    }



} 
