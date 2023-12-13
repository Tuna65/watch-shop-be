package com.example.watchShop.service;

import com.example.watchShop.config.objectMapper.JacksonConfiguration;
import com.example.watchShop.config.socket.SocketIOServer;
import com.google.firebase.database.annotations.NotNull;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketService {

  //  @Override
//  protected void configModelMapper(ModelMapper modelMapper) {
//    super.configModelMapper(modelMapper);
//    modelMapper.addMappings(new PropertyMap<NotificationDTO, Notification>() {
//      @Override
//      protected void configure() {
//        skip(destination.getId());
//      }
//    });
//  }
  private static Logger LOGGER = LoggerFactory.getLogger(SocketService.class);
  private final SocketIOServer socketIOServer;

  public void sendRealtimeNotification(@NotNull String dto) {
    try {
      String message =
          JacksonConfiguration.getObjectMapper().writeValueAsString(Map.of("data", dto));
      String room = dto;
      socketIOServer.getMSocketIoServer().namespace("/").broadcast(room, "notification", message);
    } catch (Exception e) {
      System.out.println(
          "Exception in NotificationSocketService.sendRealtimeNotification: " + e.getMessage());
    }
  }
}
