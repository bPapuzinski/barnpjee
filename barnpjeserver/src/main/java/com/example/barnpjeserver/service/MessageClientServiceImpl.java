package com.example.barnpjeserver.service;

import com.example.barnpjeserver.model.message.Message;
import com.example.barnpjeserver.model.message.MessageServerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MessageClientServiceImpl implements MessageClientService {
  private final RestTemplate restTemplate;
  private final MessageService messageService;
  private final MessageWriteService messageWriteService;

  @Override
  public ResponseEntity<? extends Map> sendMessage(MessageServerRequest messageRequest) {
    String url = "http://localhost:8081/message/send";
    Map<LocalDateTime, Message> type = new HashMap();
    if (messageRequest.getMessageType().equals("REST")) {
      ResponseEntity<? extends Map> result = restTemplate.postForEntity(url, messageRequest, type.getClass());
      messageWriteService.addMessageToMapFromYourself(messageRequest);
      return result;
    } else {
      Map<String, com.example.barnpjeserver.model.message.Message> result = messageService.sendMessage(new MessageServerRequest(messageRequest.getFromClientName(), messageRequest.getToClientName(), messageRequest.getMessage(), messageRequest.getMessageType()));
      messageWriteService.addMessageToMapFromYourself(messageRequest);
      return new ResponseEntity<>(result, HttpStatus.OK);
    }

  }

  @Override
  public Map<String, Message> receiveMessage(MessageServerRequest messageRequest) {
    return messageWriteService.addMessageToMapFromOtherUser(messageRequest);
  }

  @Override
  public Map<String, Message> getMessages(String client) {
    return messageWriteService.getMessages(client);
  }


}
