package com.arithmeticcalculator.domains;

import java.util.regex.Pattern;
import lombok.NonNull;
import lombok.Value;
import org.mindrot.jbcrypt.BCrypt;

@Value
public class Password {

  private static final Pattern BCRYPT_PATTERN =
      Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
  String cryptedPassword;

  private Password(@NonNull String password) {
    if (!BCRYPT_PATTERN.matcher(password).matches())
      this.cryptedPassword = encryptPassword(password);
    else this.cryptedPassword = password;
  }

  private String encryptPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public static Password newInstance(@NonNull String password) {
    return new Password(password);
  }
}
