package com.example.watchShop.repository;

import com.example.watchShop.dto.PackSizeDTO;
import com.example.watchShop.models.Packsize;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PackSizeRepository extends JpaRepository<Packsize, UUID> {

  @Query("select e from Packsize e"
      + " where (:#{#dto.productId} is null or :#{#dto.productId} = e.product.id)"
  )
  List<Packsize> find(PackSizeDTO dto);
}
