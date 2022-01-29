package com.example.barnpjeserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExceptionResponse {
  private String message;
  private String reason;
}
