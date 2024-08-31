package com.ecom.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class LoginResponse {

  private String id;

  private String message;


  public LoginResponse() {
  }

  public LoginResponse(String id, String message) {
    this.id = id;
    this.message = message;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "LoginResponse{" +
        "id='" + id + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
