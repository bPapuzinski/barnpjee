package com.example.barnpjeserver.model.client;

import com.example.barnpjeserver.model.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientRegisterRequest {

  private String clientPublicName;
  private String clientURL;
  private MessageTypeEnum messageType;
}
