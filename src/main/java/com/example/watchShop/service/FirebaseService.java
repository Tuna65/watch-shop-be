package com.example.watchShop.service;


import com.example.watchShop.dto.FirebaseDTO;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

  public void sendToFirebase(FirebaseDTO dto, Long userId) {
    String topic =
        String.format(
            "base_app.firebase.topic_%s",
            userId.toString());
    try {
      Message message =
          Message.builder()
              .setNotification(
                  com.google.firebase.messaging.Notification.builder()
                      .setBody(dto.getBody())
                      .setTitle(dto.getTitle())
                      .setImage(dto.getImage())
                      .build())
              .setTopic(topic)
              .build();
      FirebaseMessaging.getInstance().send(message);
    } catch (FirebaseMessagingException e) {
      System.out.println(
          "NotificationService.send - FirebaseMessagingException: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("NotificationService.send - Exception: " + e.getMessage());
    }
  }
}
