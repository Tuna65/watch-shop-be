package com.example.watchShop.repository;

import com.example.watchShop.models.Brand;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {

  @Query("select e from Brand e"
      + " where e.status = 'ACTIVE'"
  )
  List<Brand> findAllByStatus();
}
