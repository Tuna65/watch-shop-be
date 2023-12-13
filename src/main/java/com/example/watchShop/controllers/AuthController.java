package com.example.watchShop.controllers;

import com.example.watchShop.dto.ProductDTO;
import com.example.watchShop.dto.UserDTO;
import com.example.watchShop.enums.Status;
import com.example.watchShop.exception.InvalidatedException;
import com.example.watchShop.jwt.JwtRequest;
import com.example.watchShop.jwt.JwtResponse;
import com.example.watchShop.jwt.JwtTokenUtil;
import com.example.watchShop.models.User;
import com.example.watchShop.repository.UserRepository;
import com.example.watchShop.service.ProductService;
import com.example.watchShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
      throws Exception {

    User user = userRepository.findByUserNameAndStatus(authenticationRequest.getEmail(),
        Status.ACTIVE);

    if (user == null) {
      throw new InvalidatedException("Tài khoản/Mật khẩu không chính xác!");
    } else if (Status.INACTIVE == user.getStatus()) {
      throw new InvalidatedException("Tài khoản đã bị khóa!");
    }

    authenticate(authenticationRequest.getEmail(),
        authenticationRequest.getPassword());

    final String token = jwtTokenUtil.generateToken(user);
    JwtResponse jwtResponse = new JwtResponse(token, user.getId(), user.getUserName()
        , jwtTokenUtil.getExpirationDateFromToken(token));
    return ResponseEntity.ok(jwtResponse);
  }

  //Xác thực
  private void authenticate(String username, String password) throws InvalidatedException {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
    } catch (BadCredentialsException e) {
      throw new InvalidatedException("Tài khoản/mật khẩu không chính xác!");
    }
  }

  public UserDTO register(UserDTO dto) throws InvalidatedException {
    return userService.register(dto);
  }

}
