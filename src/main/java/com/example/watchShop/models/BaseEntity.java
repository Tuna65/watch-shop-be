package com.example.watchShop.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public abstract class BaseEntity {

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  private Date createdAt;

  @Column(name = "updated_at", nullable = false)
  @LastModifiedDate
  private Date updatedAt;

  @CreatedBy
  @Columns(columns = {@Column(name = "created_by", updatable = false)})
  private Long createdBy;

  @LastModifiedBy
  @Columns(columns = {@Column(name = "updated_by")})
  private Long updatedBy;
}