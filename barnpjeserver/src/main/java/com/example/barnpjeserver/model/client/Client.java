package com.example.barnpjeserver.model.client;

import com.example.barnpjeserver.model.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Client {

  private String publicName;
  private String uri;
  private MessageTypeEnum messageType;
}
