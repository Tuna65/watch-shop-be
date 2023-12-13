package com.example.watchShop.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackSizeDTO {

  private UUID id;

  private Long price;

  private String name;

  private String image;

  private UUID productId;
}
