package com.example.watchShop.controllers;

import com.example.watchShop.dto.CommentDTO;
import com.example.watchShop.dto.MsgResponse;
import com.example.watchShop.dto.PageResponse;
import com.example.watchShop.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @PostMapping
  public CommentDTO create(@RequestBody CommentDTO dto) {
    return commentService.create(dto);
  }

  @PutMapping("/{id}")
  public CommentDTO update(@RequestBody CommentDTO dto, @PathVariable UUID id) {
    return commentService.update(dto, id);
  }

  @DeleteMapping("/{id}")
  public MsgResponse deleted(@PathVariable UUID id) {
    return commentService.deleted(id);
  }

  @GetMapping
  public PageResponse find(CommentDTO dto, Pageable pageable) {
    return new PageResponse(commentService.list(dto, pageable));
  }
}
