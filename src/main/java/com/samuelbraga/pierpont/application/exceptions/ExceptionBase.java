package com.samuelbraga.pierpont.application.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionBase extends RuntimeException {
  private HttpStatus code;

  public ExceptionBase(String message, HttpStatus code) {
    super(message);
    this.code = code;
  }
}
