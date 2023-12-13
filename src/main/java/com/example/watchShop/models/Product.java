package com.example.watchShop.models;

import com.example.watchShop.converter.StringListConverter;
import com.example.watchShop.enums.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "product")
@Getter
@Setter
@EqualsAndHashCode
public class Product {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "uuid", columnDefinition = "char(36)")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID id;

  private String name;

  private Long quantity;

  @Convert(converter = StringListConverter.class)
  @Column(columnDefinition = "TEXT")
  private List<String> images;

  @Column(columnDefinition = "TEXT")
  private String description;

  private Long price;

  private Long saleOff;

  private String sku;

  @Enumerated(EnumType.STRING)
  private Status status;

  private String defaultUnitName;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "packsize_id")
  private List<Packsize> packsizes = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "comment_id")
  private List<Comment> comments = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "product_group_id")
  private ProductGroup productGroup;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "brand")
  private Brand brand;

}
