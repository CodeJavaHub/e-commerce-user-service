package com.ecom.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User implements UserDetails {

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

  @CreationTimestamp
  @Column(updatable = false, name = "createdOn")
  private Date createdOn;

  @UpdateTimestamp
  @Column(name = "updatedOn")
  private Date updatedOn;

  @Column(name = "isLocked")
  private Boolean isLocked;

  @Column(name = "isEnable")
  private Boolean isEnable;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
