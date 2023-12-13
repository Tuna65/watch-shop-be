package com.example.watchShop.service;

import com.example.watchShop.dto.UserDTO;
import com.example.watchShop.exception.InvalidatedException;
import com.example.watchShop.models.User;
import com.example.watchShop.repository.UserRepository;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserDTO> {

  @Autowired
  private UserRepository userRepository;

  public UserDTO findDTOByIdAndStatus(UUID id) {
    User user = checkNotFound(id);
    return mapToDTO(user);
  }

  public UserDTO register(UserDTO dto) throws InvalidatedException {
    User user = userRepository.findByEmail(dto.getEmail());
    if (user != null) {
      throw new InvalidatedException("Email đã tồn tại!");
    }
    User user1 = mapToEntity(dto);
    user1.setIsAdmin(false);
    return mapToDTO(userRepository.save(user1));
  }

  //validate
  public User checkNotFound(UUID id) {
    User user = userRepository.findByIdAndStatus(id);
    if (user == null) {
      throw new EntityNotFoundException("Có lỗi xảy ra vui lòng thử lại!");
    }
    return user;
  }
}
