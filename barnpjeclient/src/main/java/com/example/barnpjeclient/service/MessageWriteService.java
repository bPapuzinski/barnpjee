package com.example.barnpjeclient.service;

import com.example.barnpjeclient.model.Message;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.barnpjeserver.model.message.MessageServerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageWriteService {
  private final Map<String, Map<String, Message>> messageMap = new HashMap();

  public Map<String, Message> addMessageToMapFromYourself(MessageServerRequest messageRequest) {
    Map<String, Message> mapOfMessageFromUser = messageMap.get(messageRequest.getToClientName());
    if(mapOfMessageFromUser == null) {
      mapOfMessageFromUser = new HashMap<>();
      mapOfMessageFromUser.put(LocalDateTime.now().toString(), new Message(messageRequest.getFromClientName(), messageRequest
          .getToClientName(), messageRequest.getMessage()));
      messageMap.put(messageRequest.getToClientName(), mapOfMessageFromUser);
    } else {
      mapOfMessageFromUser.put(LocalDateTime.now().toString(), new Message(messageRequest.getFromClientName(), messageRequest
          .getToClientName(), messageRequest.getMessage()));
    }
    return mapOfMessageFromUser;
  }

  public Map<String, Message> addMessageToMapFromOtherUser(MessageServerRequest messageRequest) {
    Map<String, Message> mapOfMessageFromUser = messageMap.get(messageRequest.getFromClientName());
    if(mapOfMessageFromUser == null) {
      mapOfMessageFromUser = new HashMap<>();
      mapOfMessageFromUser.put(LocalDateTime.now().toString(), new Message(messageRequest.getFromClientName(), messageRequest
          .getToClientName(), messageRequest.getMessage()));
      messageMap.put(messageRequest.getFromClientName(), mapOfMessageFromUser);
    } else {
      mapOfMessageFromUser.put(LocalDateTime.now().toString(), new Message(messageRequest.getFromClientName(), messageRequest
          .getToClientName(), messageRequest.getMessage()));
    }
    return mapOfMessageFromUser;
  }

  public Map<String, Message> getMessages(String client) {
    if (messageMap.get(client) != null) {
      return messageMap.get(client)
          .entrySet()
          .stream()
          .sorted((e1, e2) -> {
//          DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime e1DateTime = LocalDateTime.parse(e1.getKey());
            LocalDateTime e2DateTime = LocalDateTime.parse(e2.getKey());
            return e2DateTime.compareTo(e1DateTime);
          }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
              (e1, e2) -> e1, HashMap::new));
    } else {
      return null;
    }

  }

}
