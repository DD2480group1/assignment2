package com.sendermail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    //This function is based on the tutorial by Rishabh Mishra called How To Send Email In Java Using Gmail SMTP
    public static boolean sendMail(String compile, String test, String issue, String email) {
        boolean sent = false;

        String to = email;
        String from = "mailt7458@gmail.com";
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("mailt7458@gmail.com", "jjpgkqaelbdkmemw");

            }

        });

        // Debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is a push for " + issue);

            //Prep status message
            String status = "";

            String[] lines = compile.split("\n");
            int errorCode = Integer.parseInt(lines[lines.length-1]);
            if (errorCode == 0){
                status = status + "THE BUILD OF THE PROJECT WAS SUCCESSFUL \n";
            } else{
                status = status + "THE BUILD OF THE PROJECT WAS UNSUCCESSFUL \n";
            }
            lines = test.split("\n");
            errorCode = Integer.parseInt(lines[lines.length-1]);
            if (errorCode == 0){
                status = status + "THE RUNNING OF THE TESTS WAS SUCCESSFUL \n";
            } else{

                status = status + "THE RUNNING OF THE TESTS WAS UNSUCCESSFUL \n";
            }
            status = status + "See additional build and test details below \n";
            status = status +  "-------------------------------------------";

            // Set email message
            message.setText(status + compile + "\n" + test);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            sent = true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return sent;
    }

}
