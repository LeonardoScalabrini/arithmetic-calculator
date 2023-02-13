package com.arithmeticcalculator.entities;

import com.arithmeticcalculator.security.Privileges;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Immutable
@Entity(name = "user_table")
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UserEntity implements Serializable {
  @Id @EqualsAndHashCode.Exclude private final String id = UUID.randomUUID().toString();
  @NotBlank private String email;
  @NotBlank @EqualsAndHashCode.Exclude private String password;
  @NotNull private Privileges privileges;
  @Setter private double balance;

  private UserEntity() {};

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
}
