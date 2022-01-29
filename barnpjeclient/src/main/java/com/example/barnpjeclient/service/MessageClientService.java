package com.example.barnpjeclient.service;

import com.example.barnpjeclient.model.Message;
import java.time.LocalDateTime;
import java.util.Map;

import com.example.barnpjeserver.model.message.MessageServerRequest;
import org.springframework.http.ResponseEntity;

public interface MessageClientService {

  ResponseEntity<? extends Map> sendMessageClient(MessageServerRequest messageRequest);

  Map<String, Message> receiveMessage(MessageServerRequest messageRequest);

  Map<String, Message> getMessages(String client);
}
