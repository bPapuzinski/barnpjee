package com.example.barnpjeserver.controller;

import com.example.barnpjeserver.model.message.MessageServerRequest;
import com.example.barnpjeserver.service.MessageServiceImpl;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {
  private final MessageServiceImpl messageServiceImpl;


  @PostMapping("/message/send")
  public ResponseEntity<? extends Map> sendMessageServer(@RequestBody MessageServerRequest request) {
    return new ResponseEntity<>(messageServiceImpl.sendMessage(request), HttpStatus.OK);
  }
}
