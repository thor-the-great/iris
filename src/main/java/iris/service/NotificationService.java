package iris.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class NotificationService {

    public void sendEmail(String toRecipient, String messageBody) {
        final String username = "android.developer.thor@gmail.com";
        final String password = "F8L4Xl8FRSCd";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("iris_notificator@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toRecipient));
            message.setSubject("Testing Subject");
            message.setText(messageBody);
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        service.sendEmail("gthor10@gmail.com",
                "Dear Mail Crawler,"
                + "\n\nTesting delivery system of Iris project");
    }
}
