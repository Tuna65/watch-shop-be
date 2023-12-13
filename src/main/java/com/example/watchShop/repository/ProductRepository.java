package com.example.watchShop.repository;

import com.example.watchShop.dto.ProductDTO;
import com.example.watchShop.enums.Status;
import com.example.watchShop.models.Product;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface ProductRepository extends JpaRepository<Product, UUID> {

  Product findByIdAndStatus(UUID id, Status status);

  @Query("select e from Product e"
      + " where e.status = 'ACTIVE'"
      + " and (:#{#dto.brandId} is null or :#{#dto.brandId} = e.brand.id)"
      + " and (upper(e.name) like upper(concat('%',:#{#dto.name},'%'))) "
  )
  Page<Product> search(ProductDTO dto, Pageable pageable);

  @Query("select e from Product e"
      + " where e.status = 'ACTIVE'"
      + " order by e.price asc "
  )
  Page<Product> bestSaler(Pageable pageable);

}
