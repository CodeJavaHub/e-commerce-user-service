package com.ecom.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDto {

  private Long id;

  // @NotNull(message = "firstName cannot null!")
  private String firstName;

  // @NotNull(message = "lastName cannot null!")
  private String lastName;

  // @NotNull(message = "email cannot null!")
  private String email;

  // @NotNull(message = "phone cannot null!")
  private String phone;

  // @NotNull(message = "gender cannot null!")
  private String gender;

  // @NotNull(message = "role cannot null!")
  private String role;

  // @NotNull(message = "password cannot null!")
  private String password;
}
