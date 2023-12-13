package com.example.watchShop.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

public class FirabaseConfig {

  @Value("${app.gc-file}")
  private String configPath;

  @PostConstruct
  public void initialize() {
    try {
      FirebaseOptions options =
          FirebaseOptions.builder()
              .setCredentials(
                  GoogleCredentials.fromStream(new ClassPathResource(configPath).getInputStream()))
              .build();
      if (FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options);
        System.out.println("Firebase application has been initialized");
      }
    } catch (Exception e) {
//      e.printStackTrace();
    }
  }
}