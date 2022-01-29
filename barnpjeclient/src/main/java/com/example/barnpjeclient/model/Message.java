package com.example.barnpjeclient.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Message implements Serializable {
  private String fromClientName;
  private String toClientName;
  private String message;
}
