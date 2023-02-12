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

@Immutable
@Entity(name = "user")
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UserEntity implements Serializable {
  @Id @EqualsAndHashCode.Exclude @Builder.Default private String id = UUID.randomUUID().toString();
  @NotBlank private String name;
  @NotBlank private String email;
  @NotBlank private String password;
  @NotNull private Privileges privileges;

  private UserEntity() {};

  private UserEntity(
      @NonNull String id,
      @NonNull String name,
      @NonNull String email,
      @NonNull String password,
      @NonNull Privileges privileges) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.privileges = privileges;
  }
}
