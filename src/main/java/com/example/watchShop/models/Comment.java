package com.example.watchShop.models;

import com.example.watchShop.enums.Status;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "comment")
public class Comment {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "uuid", columnDefinition = "char(36)")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID id;

  private String userName;

  private String email;

  private Long phone;

  private String content;

  @Column(columnDefinition = "char(36)")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID productId;

  @Enumerated(EnumType.STRING)
  private Status status;

  private Date createdAt;
}
