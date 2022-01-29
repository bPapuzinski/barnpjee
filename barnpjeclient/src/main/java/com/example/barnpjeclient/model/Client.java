package com.example.barnpjeclient.model;

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
