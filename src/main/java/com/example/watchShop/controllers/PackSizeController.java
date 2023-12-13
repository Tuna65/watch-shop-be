package com.example.watchShop.controllers;

import com.example.watchShop.dto.MsgResponse;
import com.example.watchShop.dto.PackSizeDTO;
import com.example.watchShop.exception.InvalidatedException;
import com.example.watchShop.service.PackSizeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pack-size")
public class PackSizeController {

  @Autowired
  private PackSizeService packSizeService;

  @PostMapping
  public MsgResponse create(@RequestBody PackSizeDTO dto) throws InvalidatedException {
    return packSizeService.create(dto);
  }

  @GetMapping
  public List<PackSizeDTO> find(PackSizeDTO dto){
    return packSizeService.findByProductId(dto);
  }
}
