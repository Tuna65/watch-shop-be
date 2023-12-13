package com.example.watchShop.controllers;

import com.example.watchShop.dto.MsgResponse;
import com.example.watchShop.dto.PackSizeDTO;
import com.example.watchShop.dto.PageResponse;
import com.example.watchShop.dto.ProductDTO;
import com.example.watchShop.exception.InvalidatedException;
import com.example.watchShop.service.PackSizeService;
import com.example.watchShop.service.ProductService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private PackSizeService packSizeService;

  @PostMapping
  public ProductDTO create(@RequestBody ProductDTO dto) {
    return productService.create(dto);
  }

  @PutMapping("/{id}")
  public ProductDTO update(@RequestBody ProductDTO dto, @PathVariable UUID id)
      throws InvalidatedException {
    return productService.update(dto, id);
  }

  @DeleteMapping("/{id}")
  public MsgResponse delete(@PathVariable UUID id) throws InvalidatedException {
    return productService.delete(id);
  }

  @GetMapping("/{id}")
  public ProductDTO detail(@PathVariable UUID id) throws InvalidatedException {
    return productService.detail(id);
  }

  @GetMapping
  public PageResponse list(ProductDTO dto, Pageable pageable) {
    return new PageResponse(productService.search(dto, pageable));
  }

  @GetMapping("/best-saler")
  public PageResponse bestSaler(Pageable pageable) {
    return new PageResponse(productService.bestSaler(pageable));
  }

  @GetMapping("/pack-size")
  public List<PackSizeDTO> find(PackSizeDTO dto) {
    return packSizeService.findByProductId(dto);
  }

  @PostMapping("/pack-size")
  public MsgResponse create(@RequestBody PackSizeDTO dto) throws InvalidatedException {
    return packSizeService.create(dto);
  }
}
