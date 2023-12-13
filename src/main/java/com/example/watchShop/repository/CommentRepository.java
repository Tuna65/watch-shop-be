package com.example.watchShop.repository;

import com.example.watchShop.dto.CommentDTO;
import com.example.watchShop.enums.Status;
import com.example.watchShop.models.Comment;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

  Comment findByIdAndStatus(UUID id, Status status);

  @Query("select e from Comment e"
      + " where e.status = 'ACTIVE'"
      + " and (:#{#dto.productId} is null or :#{#dto.productId} = e.productId)"
      + " order by e.createdAt desc"
  )
  Page<Comment> find(CommentDTO dto, Pageable pageable);
}
