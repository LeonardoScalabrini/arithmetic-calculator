package com.arithmeticcalculator.security;

import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
import java.util.Collection;
import java.util.Collections;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class UserDetailsServiceImpl implements UserDetailsService {

  private final UserEntityJpaRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(@NonNull UserEntityJpaRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
    return userRepository
        .findByEmail(email)
        .map(u -> new User(u.getEmail(), u.getPassword(), getGrantedAuthority(u)))
        .orElseThrow(
            () -> new UsernameNotFoundException(String.format("Email %s not found", email)));
  }

  private Collection<GrantedAuthority> getGrantedAuthority(@NonNull UserEntity user) {
    return Collections.singletonList(new SimpleGrantedAuthority(user.getPrivileges().getRole()));
  }
}
