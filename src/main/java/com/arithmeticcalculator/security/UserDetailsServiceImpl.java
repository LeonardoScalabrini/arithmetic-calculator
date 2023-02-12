package com.arithmeticcalculator.security;

import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.repositories.UserEntityJpaRepository;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserEntityJpaRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserEntityJpaRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository
        .findByEmail(email)
        .map(u -> new User(u.getEmail(), u.getPassword(), getGrantedAuthority(u)))
        .orElseThrow(
            () -> new UsernameNotFoundException(String.format("Email %s not found", email)));
  }

  private Collection<GrantedAuthority> getGrantedAuthority(UserEntity user) {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getPrivileges().getRole()));
    return authorities;
  }
}
