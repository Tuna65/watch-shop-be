package com.example.watchShop.service;

import com.example.watchShop.config.audit.DefaultUserPrincipal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

  public DefaultUserPrincipal getUserPrincipal() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()
        || authentication instanceof AnonymousAuthenticationToken) {
      return null;
    }

    return (DefaultUserPrincipal) authentication.getPrincipal();
  }

  public UUID getCurrentUserId() {
    return getUserPrincipal() != null ? getUserPrincipal().getId() : null;
  }

  public final boolean hasAuthority(String authority) {
    return hasAnyAuthority(authority);
  }

  public final boolean hasAnyAuthority(String... authorities) {
    return hasAnyAuthorityName(null, authorities);
  }

  public final boolean hasRole(String role) {
    return hasAnyRole(role);
  }

  public final boolean hasAnyRole(String... roles) {
    return hasAnyAuthorityName("ROLE_", roles);
  }

  private boolean hasAnyAuthorityName(String prefix, String... roles) {
    Set<String> roleSet = getAuthoritySet();

    for (String role : roles) {
      String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
      if (roleSet.contains(defaultedRole)) {
        return true;
      }
    }

    return false;
  }

  private Set<String> getAuthoritySet() {
    DefaultUserPrincipal user = getUserPrincipal();

    if (user == null) {
      return new HashSet<>();
    }

    return AuthorityUtils.authorityListToSet(user.getAuthorities());
  }

  private String getRoleWithDefaultPrefix(String defaultRolePrefix, String role) {
    if (role == null) {
      return null;
    }

    if (defaultRolePrefix == null || defaultRolePrefix.length() == 0) {
      return role;
    }

    if (role.startsWith(defaultRolePrefix)) {
      return role;
    }

    return defaultRolePrefix + role;
  }


  public Boolean checkAuthority(String authority) {
    if (!hasAnyAuthority(authority)) {
//      throw new AccessDeniedException(Const.RoleUnauthorMessage.get(authority));
    }
    return true;
  }

//  public Boolean checkCreateAtAndRole(String permission, Long userId) {
//    return Objects.equals(userId, getCurrentUserId()) || hasAnyAuthority(
//        permission);
//  }
}
