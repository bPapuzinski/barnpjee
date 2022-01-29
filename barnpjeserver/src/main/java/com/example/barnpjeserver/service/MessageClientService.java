package com.example.barnpjeserver.service;

import com.example.barnpjeserver.model.message.Message;
import com.example.barnpjeserver.model.message.MessageServerRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface MessageClientService {

  ResponseEntity<? extends Map> sendMessage(MessageServerRequest messageRequest);

  Map<String, Message> receiveMessage(MessageServerRequest messageRequest);

  Map<String, Message> getMessages(String client);
}
