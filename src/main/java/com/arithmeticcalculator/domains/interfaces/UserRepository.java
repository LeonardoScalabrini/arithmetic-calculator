package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.User;
import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> findByEmail(String email);
}
