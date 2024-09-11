package com.ecom.userservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
  private Long id;
  private String email;
  private String firstName;
  private String lastName;
  private String role;
}
