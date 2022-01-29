package com.example.barnpjeclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegisterRequest {
  private String clientPublicName;
  private String clientURL;
  private MessageTypeEnum messageType;
}
