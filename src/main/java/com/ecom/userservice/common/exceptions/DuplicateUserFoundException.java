package com.ecom.userservice.common.exceptions;

public class DuplicateUserFoundException extends RuntimeException {

  public DuplicateUserFoundException(String message) {
    super(message);
  }

}
