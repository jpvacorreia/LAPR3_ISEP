package lapr.project.data.api;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendEmail {

    public SendEmail() {
    }

    public boolean sendEmail(String recipient, String subject, String body){
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.mailtrap.io");
        properties.put("mail.smtp.port", "587");

        String companyEmail = "lapr3g40@example.com";
        String companyUser = "06b9bce0dd1a5e";
        String companyPWD = "eac983baecbc6c";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(companyUser, companyPWD);
            }
        });

        Message message = prepareMessage(session, companyEmail, subject , body, recipient);
        try {
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Message prepareMessage(Session session, String companyEmail, String subject, String body, String recipient){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(companyEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
