package com.example.watchShop.config.socket;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketIOServlet extends HttpServlet {

  @Autowired
  private SocketIOServer socketIOServer;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    if(socketIOServer != null && socketIOServer.getMEngineIoServer() != null){
      try{
        socketIOServer.getMEngineIoServer().handleRequest(request, response);
      } catch (Exception e){

      }
    }
  }
}