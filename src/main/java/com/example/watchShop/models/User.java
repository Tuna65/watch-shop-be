package com.example.watchShop.models;

import com.example.watchShop.enums.Status;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "user_account")
@Getter
@Setter
@EqualsAndHashCode
public class User {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "uuid", columnDefinition = "char(36)")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID id;


  private String fullName;

  private String userName;

  private String passWord;

  private Status status;

  private String email;

  private String address;

  private Long phone;

  private String image;

  private Boolean isAdmin;

}
