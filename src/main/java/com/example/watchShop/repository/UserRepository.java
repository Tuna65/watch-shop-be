package com.example.watchShop.repository;

import com.example.watchShop.enums.Status;
import com.example.watchShop.models.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  User findByUserNameAndStatus(String userName, Status status);

  @Query("select e from User e"
      + " where e.status = 'ACTIVE'"
      + " and e.id = :id"
  )
  User findByIdAndStatus(UUID id);

  User findByEmail(String email);

}
