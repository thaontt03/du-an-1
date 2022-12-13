package edu.poly.duan1.ultis;

import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Message;
import jakarta.mail.Authenticator;
import jakarta.mail.Transport;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
//import java.util.Calendar;

public class MailSender {

    public void MailSender(String email, String ma) {
        final String username = "rest.bookstore@gmail.com";
        final String password = "zwsnybendwofmixo";
        Date date = java.util.Calendar.getInstance().getTime();

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rest.bookstore@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Mat khau moi  " + date);
            //message.setText("ma xac minh cua ban la: " + ma);
            message.setText("Mat khau cua ban la: " + ma + "\nVui long khong chia se mat khau nay cho bat ki ai" + "\nxin cam on");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
