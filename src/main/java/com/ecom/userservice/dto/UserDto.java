package com.ecom.userservice.dto;

import jakarta.persistence.Id;


public class UserDto {

  @Id private Long id;

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

  public UserDto() {
  }

  public UserDto(Long id, String firstName, String lastName, String email, String phone,
      String gender, String role, String password) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.gender = gender;
    this.role = role;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserDto{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", gender='" + gender + '\'' +
        ", role='" + role + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
