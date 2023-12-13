package com.example.watchShop.config.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfiguration {

  @Autowired
  private SocketIOServlet socketIOServlet;

  @Bean
  public ServletRegistrationBean<SocketIOServlet> servletRegistrationBean() {
    return new ServletRegistrationBean<>(socketIOServlet, "/ws/*");
  }
}
