package co.edu.uniquindio;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jacoboarboleda511@gmail.com", "glcq hxhd mfod hkpo");
            }
        });
        MimeMessage message = new MimeMessage(session);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("juanfe0151@gmail.com", true));
            message.setSubject("Cita");
            message.setText("Tiene una cita programada para");
            System.out.println("Enviando mensaje...");
            Transport.send(message);
            System.out.println("Mensaje enviado");
        } catch (MessagingException me) {
            System.out.println("Exception:" + me);
        }


    }
}