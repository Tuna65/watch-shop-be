package com.example.watchShop.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.util.CastUtils;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class PageResponse {

  private Object data = null;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Long totalElements;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Integer totalPages;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Integer pageNumber;

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Integer pageSize;

  public PageResponse(Object data) {
    if (data instanceof Page) {
      Page<?> page = CastUtils.cast(data);
      this.totalPages = page.getTotalPages();
      this.totalElements = page.getTotalElements();
      this.pageSize = page.getSize();
      this.pageNumber = page.getNumber();
      this.data = page.getContent();
    }
  }

}