package com.arithmeticcalculator.domains;

import lombok.NonNull;
import lombok.Value;
import org.mindrot.jbcrypt.BCrypt;

@Value
public class Password {
  String cryptedPassword;

  private Password(@NonNull String password) {
    this.cryptedPassword = encryptPassword(password);
  }

  private String encryptPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public boolean check(@NonNull String password) {
    return BCrypt.checkpw(password, cryptedPassword);
  }

  public static Password newInstance(@NonNull String password) {
    return new Password(password);
  }
}
