package com.samuelbraga.pierpont.application.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionBase extends RuntimeException {
  private final HttpStatus code;

  public ExceptionBase(String message, HttpStatus code) {
    super(message);
    this.code = code;
  }
}
