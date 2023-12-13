package com.example.watchShop.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseDTO extends BaseDTO implements Serializable {
  private String title;

  private String body;

  private String image;
}