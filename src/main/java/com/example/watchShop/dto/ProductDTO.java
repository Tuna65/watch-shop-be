package com.example.watchShop.dto;

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
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

  private UUID id;

  private String name;

  private Long quantity;

  private List<String> images;

  private String description;

  private Long price;

  private Long saleOff;

  private String sku;

  private String defaultUnitName;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<UUID> commentIds;

  @JsonProperty(access = Access.WRITE_ONLY)
  private UUID productGroupId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private UUID brandId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<UUID> rateIds;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<UUID> packsizeIds;
}
