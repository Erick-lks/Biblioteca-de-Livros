package com.caetano.demo.exceptions;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
  protected LocalDateTime timestamp;
  protected int status;
  protected   String details;
  protected String developerMessage;
  protected String title;
}
