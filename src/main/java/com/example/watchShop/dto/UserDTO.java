package com.example.watchShop.dto;

import com.example.watchShop.enums.Status;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends BaseDTO{
  private UUID id;

  private String fullName;

  private String userName;

  private Status status;

  private String email;

  private String address;

  private Long phone;

  private String image;
}
