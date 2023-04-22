package com.arithmeticcalculator.ports.out;

import com.arithmeticcalculator.domains.User;
import java.util.Optional;

public interface UserRepositoryInterface {

  void save(User user);

  Optional<User> findByEmail(String email);
}
