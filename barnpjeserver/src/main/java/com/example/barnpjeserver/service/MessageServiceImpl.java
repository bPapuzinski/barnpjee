package com.example.barnpjeserver.service;

import com.example.barnpjeserver.exception.MessageSendUserToHimself;
import com.example.barnpjeserver.exception.UserDoNotExist;
import com.example.barnpjeserver.model.MessageTypeEnum;
import com.example.barnpjeserver.model.client.Client;
import com.example.barnpjeserver.model.message.Message;
import com.example.barnpjeserver.model.message.MessageServerRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final RestTemplate restTemplate;

  private final ClientService clientService;

  private final MessageClientService messageClientService;

  private final HessianProxyFactoryBean hessianProxyFactoryBean;

  @Override
  public Map<String, Message> sendMessage(MessageServerRequest messageServerRequest) {
    if (messageServerRequest
        .getFromClientName().toLowerCase()
        .equals(messageServerRequest.getToClientName().toLowerCase())) {
      throw new MessageSendUserToHimself(
          String.format("User: %s trying to send message to himself", messageServerRequest
              .getFromClientName()));
    }

    if (!clientService.isUserRegistered(messageServerRequest.getFromClientName())) {
      throw new UserDoNotExist("User have to register, before send message");
    }

    if (!clientService.isUserRegistered(messageServerRequest.getToClientName())) {
      throw new UserDoNotExist("User do not exist");
    }
    Client toClient = clientService.getClientByUserName(messageServerRequest.getToClientName());
    if (toClient.getMessageType() == MessageTypeEnum.REST) {
      return sendREST(messageServerRequest, toClient);
    } else {
      return sendHESSIAN(messageServerRequest, toClient);
    }
  }

  private Map sendREST(MessageServerRequest messageServerRequest,
      Client client) {
    String messageToClientUri = client.getUri() + "/message/receive";
    Map<String, Message> type = new HashMap();
    return restTemplate.postForObject(messageToClientUri, messageServerRequest, type.getClass());
  }

  private Map sendHESSIAN(MessageServerRequest messageServerRequest,
      Client client) {
    hessianProxyFactoryBean.setServiceUrl(client.getUri() + "/hellohessian");
    return messageClientService.receiveMessage(
            new MessageServerRequest(messageServerRequest.getFromClientName(),
                messageServerRequest.getToClientName(),
                messageServerRequest.getMessage(),
                messageServerRequest.getMessageType()));
  }
}
