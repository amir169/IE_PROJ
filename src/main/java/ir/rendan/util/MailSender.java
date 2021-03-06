package ir.rendan.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
class Mailer{
    public static void send(String from,String password,String to,String sub,String msg) throws MessagingException {
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message

        MimeMessage message = new MimeMessage(session);
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
        message.setSubject(sub);
        message.setText(msg);
        message.setFrom(new InternetAddress(from));
        //send message
        Transport.send(message);

    }
}
public class MailSender {

    public static void sendEmail(String address,String subject,String message) throws MessagingException {
        Mailer.send("sbu.aichallenge@gmail.com","aic123456"
                ,address,subject,message);
    }
}    