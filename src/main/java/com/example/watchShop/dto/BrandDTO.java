package com.example.watchShop.dto;

import com.example.watchShop.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO extends BaseDTO {

  private UUID id;

  private String image;

  private String name;

  private List<UUID> productIds;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Status status;
}
