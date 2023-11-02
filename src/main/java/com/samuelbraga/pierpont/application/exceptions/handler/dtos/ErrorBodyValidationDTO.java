package com.samuelbraga.pierpont.application.exceptions.handler.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorBodyValidationDTO {
  private String field;
  private String message;
}
