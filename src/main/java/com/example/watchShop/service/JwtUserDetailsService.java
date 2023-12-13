package com.example.watchShop.service;

import com.example.watchShop.config.audit.DefaultUserPrincipal;
import com.example.watchShop.enums.Status;
import com.example.watchShop.models.User;
import com.example.watchShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserNameAndStatus(username, Status.ACTIVE);
    if (user == null) {
      throw new UsernameNotFoundException("Tài khoản/Mật khẩu không chính xác");
    } else if (Status.INACTIVE == user.getStatus()) {
      throw new UsernameNotFoundException("Tài khoản đã bị khóa");
    }
    DefaultUserPrincipal userPrincipal = new DefaultUserPrincipal(user.getId(), user.getUserName(),
        user.getPassWord(), user.getFullName());
    return userPrincipal;
  }
//  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
}