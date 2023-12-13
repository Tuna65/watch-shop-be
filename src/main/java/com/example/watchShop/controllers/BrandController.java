package com.example.watchShop.controllers;

import com.example.watchShop.dto.BaseDTO;
import com.example.watchShop.dto.BrandDTO;
import com.example.watchShop.dto.MsgResponse;
import com.example.watchShop.service.BrandService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

  @Autowired
  private BrandService brandService;

  @PostMapping
  public BaseDTO create(@RequestBody BrandDTO dto) {
    return brandService.create(dto);
  }

  @DeleteMapping("/{id}")
  public MsgResponse delete(@PathVariable UUID id) {
    return brandService.delete(id);
  }

  @GetMapping
  public List<BrandDTO> getAll() {
    return brandService.getAll();
  }

}
