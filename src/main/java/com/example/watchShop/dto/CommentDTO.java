package com.example.watchShop.dto;

import com.example.watchShop.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Date;
import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

  private UUID id;

  private String userName;

  private String email;

  private Long phone;

  private String content;

  private UUID productId;

  @Enumerated(EnumType.STRING)
  @JsonProperty(access = Access.WRITE_ONLY)
  private Status status;

  private Date createdAt;
}
