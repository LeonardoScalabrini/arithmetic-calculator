package com.arithmeticcalculator.entities;

import com.arithmeticcalculator.domains.Privileges;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.ids.UserId;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Immutable
@Entity(name = "users")
@Getter
@ToString
@EqualsAndHashCode
@Builder
public final class UserEntity {
  @EmbeddedId @EqualsAndHashCode.Exclude private UserId id;

  @NotNull @NotBlank private String email;

  @NotBlank @EqualsAndHashCode.Exclude private String password;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Privileges privileges;

  @Setter private double balance;

  public UserEntity() {};

  private UserEntity(
      @NonNull UserId id,
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
    return User.builder().userId(id).email(email).balance(balance).build();
  }
}
