package com.samuelbraga.pierpont.application.exceptions.handler.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionBaseDTO {
  private String message;
  private HttpStatus code;

  public ExceptionBaseDTO(HttpStatus code, String message) {
    this.message = message;
    this.code = code;
  }
}
