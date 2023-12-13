package com.example.watchShop.utils;


import com.example.watchShop.config.objectMapper.JacksonConfiguration;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public final class ResponseUtil {

  public static void writeResponse(HttpStatus httpStatus, HttpServletResponse response, String msg)
      throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(httpStatus.value());
    response.getWriter().write(JacksonConfiguration.getObjectMapper()
        .writeValueAsString(new ReponseMsg(httpStatus, msg)));
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class ReponseMsg {

    private String error;
    private String message;
    private int status;
    private Date timestamp;

    public ReponseMsg(HttpStatus httpStatus, String msg) {
      this.error = httpStatus.getReasonPhrase();
      this.message = msg;
      this.status = httpStatus.value();
      this.timestamp = new Date();
    }
  }
}