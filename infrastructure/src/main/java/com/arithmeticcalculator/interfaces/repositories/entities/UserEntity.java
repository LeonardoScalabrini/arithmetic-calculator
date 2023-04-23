package com.arithmeticcalculator.interfaces.repositories.entities;

import com.arithmeticcalculator.domains.Password;
import com.arithmeticcalculator.domains.Privileges;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.ids.UserId;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "users")
@Getter
@ToString
@EqualsAndHashCode
@Builder
public final class UserEntity {
  @Id @EqualsAndHashCode.Exclude private String id;

  @Column(unique = true)
  @NotBlank
  private String email;

  @NotBlank private String password;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Privileges privileges;

  private double balance;

  public UserEntity() {};

  private UserEntity(
      @NonNull String id,
      @NonNull String email,
      @NonNull String password,
      @NonNull Privileges privileges,
      double balance) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.privileges = privileges;
    this.balance = balance;
  }

  public User getUser() {
    return User.builder()
        .userId(UserId.getInstance(id))
        .email(email)
        .balance(balance)
        .password(Password.newInstance(password))
        .build();
  }

  public static UserEntity from(User user) {
    return new UserEntity(
        user.getUserId().getId(),
        user.getEmail(),
        user.getPassword().getCryptedPassword(),
        user.getPrivileges(),
        user.getBalance());
  }
}
