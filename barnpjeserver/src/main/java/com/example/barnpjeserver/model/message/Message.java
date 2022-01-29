package com.example.barnpjeserver.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Message {
  private String fromClientName;
  private String toClientName;
  private String message;
}
