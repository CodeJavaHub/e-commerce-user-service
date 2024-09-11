package com.ecom.userservice.common;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorMessage {

  private int status;

  private String message;

  private long timeStamp;

  private Map<String,String> errorDetails;

}
