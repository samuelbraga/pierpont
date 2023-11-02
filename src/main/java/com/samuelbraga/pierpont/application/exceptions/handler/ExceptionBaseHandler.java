package com.samuelbraga.pierpont.application.exceptions.handler;

import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.application.exceptions.handler.dtos.ExceptionBaseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionBaseHandler {

  @ExceptionHandler(ExceptionBase.class)
  public ResponseEntity<ExceptionBaseDTO> handle(ExceptionBase exception) {
    var exceptionBaseDTO = ExceptionBaseDTO
      .builder()
      .message(exception.getMessage())
      .code(exception.getCode())
      .build();
    return new ResponseEntity<>(exceptionBaseDTO, exception.getCode());
  }
}
