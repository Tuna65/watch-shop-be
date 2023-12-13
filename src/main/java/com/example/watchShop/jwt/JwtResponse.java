package com.example.watchShop.jwt;

import com.example.watchShop.enums.Permission;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse implements Serializable {

  private static final long serialVersionUID = -8091879091924046844L;
  private String token;
  private UUID userId;
  private String email;
//  private Permission permissions;
  private Date expiredDate;

}