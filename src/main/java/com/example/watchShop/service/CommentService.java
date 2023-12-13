package com.example.watchShop.service;

import com.example.watchShop.dto.CommentDTO;
import com.example.watchShop.dto.MsgResponse;
import com.example.watchShop.enums.Status;
import com.example.watchShop.models.Comment;
import com.example.watchShop.models.Product;
import com.example.watchShop.repository.CommentRepository;
import com.example.watchShop.repository.ProductRepository;
import java.util.Date;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends AbstractService<Comment, CommentDTO> {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private ProductRepository productRepository;

  @Override
  protected void specificMapToDTO(Comment comment, CommentDTO commentDTO) {
    super.specificMapToDTO(comment, commentDTO);
  }

  public CommentDTO create(CommentDTO dto) {
    Product product = productRepository.findById(dto.getProductId()).orElse(null);
    if (product == null) {
      throw new EntityNotFoundException("Có lỗi xảy ra vui lòng thử lại!");
    }
    Comment comment = mapToEntity(dto);
    comment.setCreatedAt(new Date());
    comment.setStatus(Status.ACTIVE);
    return mapToDTO(commentRepository.save(comment));
  }

  public CommentDTO update(CommentDTO dto, UUID id) {
    Comment comment = checkNotFound(id);
    mapToEntity(dto, comment);
    return mapToDTO(commentRepository.save(comment));
  }

  public MsgResponse deleted(UUID id) {
    Comment comment = checkNotFound(id);
    comment.setStatus(Status.DELETED);
    return new MsgResponse("Xóa Bình luận thành công!");
  }

  public Page<CommentDTO> list(CommentDTO commentDTO, Pageable pageable) {
    return commentRepository.find(commentDTO, pageable).map(this::mapToDTO);
  }

  //validate
  public Comment checkNotFound(UUID id) {
    Comment comment = commentRepository.findByIdAndStatus(id, Status.ACTIVE);
    if (comment == null) {
      throw new EntityNotFoundException("Có lỗi xảy ra vui lòng thử lại!");
    }
    return comment;
  }
}
