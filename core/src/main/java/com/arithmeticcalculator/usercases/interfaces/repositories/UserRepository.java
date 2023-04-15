package com.arithmeticcalculator.usercases.interfaces.repositories;

import com.arithmeticcalculator.domains.User;
import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> findByEmail(String email);
}
