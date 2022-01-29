package com.example.barnpjeclient.controller;

import com.example.barnpjeclient.model.Message;
import com.example.barnpjeclient.service.MessageClientService;

import java.util.Map;

import com.example.barnpjeserver.model.message.MessageServerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {
  private final MessageClientService messageClientServiceImpl;

  @PostMapping("/message/receive")
  public ResponseEntity<Map<String, Message>> receiveMessage(@RequestBody MessageServerRequest messageRequest) {
    return new ResponseEntity<>(messageClientServiceImpl.receiveMessage(messageRequest), HttpStatus.OK);
  }

  @PostMapping("/message/send")
  public ResponseEntity<? extends Map> sendMessageClient(@RequestBody MessageServerRequest messageRequest) {
    return messageClientServiceImpl.sendMessageClient(messageRequest);
  }

  @GetMapping("/message/{client}")
  public ResponseEntity<Map<String, Message>> getMessages(@PathVariable String client) {
    return new ResponseEntity<>(messageClientServiceImpl.getMessages(client), HttpStatus.OK);
  }
}
