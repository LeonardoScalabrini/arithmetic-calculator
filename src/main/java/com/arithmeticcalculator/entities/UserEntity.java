package com.arithmeticcalculator.entities;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.security.Privileges;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity(name = "users")
@Getter
@ToString
@EqualsAndHashCode
@Builder
public final class UserEntity {
  @Id @EqualsAndHashCode.Exclude private final String id = UUID.randomUUID().toString();

  @Immutable @NotBlank private String email;

  @Immutable @NotBlank @EqualsAndHashCode.Exclude private String password;

  @Immutable
  @NotNull
  @Enumerated(EnumType.STRING)
  private Privileges privileges;

  @Setter private double balance;

  public UserEntity() {};

  private UserEntity(
      @NonNull String email,
      @NonNull String password,
      @NonNull Privileges privileges,
      double balance) {
    this.email = email;
    this.password = new BCryptPasswordEncoder().encode(password);
    this.privileges = privileges;
    this.balance = balance;
  }

  public User getUser() {
    return User.builder().email(email).balance(balance).build();
  }
}
