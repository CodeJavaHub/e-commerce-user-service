package com.ecom.userservice.common;

import java.util.Map;

public class ErrorMessage {
  private String status;

  private Map<String,String> errorDetails;

  public ErrorMessage() {
  }

  public ErrorMessage(String status, Map<String, String> errorDetails) {
    this.status = status;
    this.errorDetails = errorDetails;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Map<String, String> getErrorDetails() {
    return errorDetails;
  }

  public void setErrorDetails(Map<String, String> errorDetails) {
    this.errorDetails = errorDetails;
  }

  @Override
  public String toString() {
    return "ErrorMessage{" +
        "status='" + status + '\'' +
        ", errorDetails=" + errorDetails +
        '}';
  }
}
