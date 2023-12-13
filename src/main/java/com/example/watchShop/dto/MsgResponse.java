package com.example.watchShop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class MsgResponse {

  private String message;

  private boolean success = true;

  private Object data;

  public MsgResponse(String message) {
    this.message = message;
  }

  public MsgResponse(String message, Object data) {
    this.message = message;
    this.data = data;
  }
}
