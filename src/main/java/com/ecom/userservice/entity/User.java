package com.ecom.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {

  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name="firstName")
  private String firstName;

  @Column(name="lastName")
  private String lastName;

  @Column(name="email")
  private String email;

  @Column(name="phone")
  private String phone;

  @Column(name="gender")
  private String gender;

  @Column(name="role")
  private String role;

  @Column(name="password")
  private String password;
}
