package com.example.watchShop.config.email;

import javax.mail.Session;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSender {

  static Session getMailSession;
  static MimeMessage mimeMessage;

  public static synchronized void sendEmail(String email, String subject, String content) {
    try {
      log.info("Start send EMAIL to " + email);

      Properties mailServerProperties = System.getProperties();

      mailServerProperties.put("mail.smtp.port", "587");
      mailServerProperties.put("mail.smtp.auth", "true");
      mailServerProperties.put("mail.smtp.starttls.enable", "true");
      mailServerProperties.put("mail.smtp.ssl.trust", "smtp.office365.com");
      mailServerProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");

      getMailSession = Session.getDefaultInstance(mailServerProperties, null);
      mimeMessage = new MimeMessage(getMailSession);
      mimeMessage.setFrom(new InternetAddress("no-reply@isofhcare.com", "BASE"));
      mimeMessage.setHeader("Content-Type", "text/plain; charset=UTF-8");
      mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

      mimeMessage.setSubject(subject, "utf-8");
      mimeMessage.setText(content, "utf-8", "html");
      // Step3
      Transport transport = getMailSession.getTransport("smtp");
      transport.connect("smtp.office365.com", "587", "Iris@2023");
      transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
      transport.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
