package com.example.barnpjeserver.service;

import com.example.barnpjeserver.exception.DuplicateClientNameException;
import com.example.barnpjeserver.exception.UserDoNotExist;
import com.example.barnpjeserver.model.client.Client;
import com.example.barnpjeserver.model.client.ClientRegisterRequest;
import com.example.barnpjeserver.model.client.ClientRegisterResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  private final List<Client> clientList = new ArrayList<>();

  public ClientRegisterResponse registerClient(ClientRegisterRequest registerRequest) {
    boolean duplicatedClientName = clientList
        .stream()
        .anyMatch(
            client ->
                client.getPublicName().toLowerCase()
                    .equals(registerRequest.getClientPublicName().toLowerCase()));
    if (duplicatedClientName) {
      throw new DuplicateClientNameException(String
          .format("Client with name of %s already exist.", registerRequest.getClientPublicName()));
    }
    clientList.add(new Client(registerRequest.getClientPublicName(), registerRequest.getClientURL(), registerRequest.getMessageType()));
    return new ClientRegisterResponse(registerRequest.getClientPublicName());
  }

  public boolean isUserRegistered(String userName) {
    return clientList
        .stream()
        .anyMatch(
            client ->
                client.getPublicName().toLowerCase()
                    .equals(userName.toLowerCase()));
  }

  public Client getClientByUserName(String userName) {
    return clientList
        .stream()
        .filter(
            client ->
                client.getPublicName().toLowerCase()
                    .equals(userName.toLowerCase()))
        .findFirst()
        .orElseThrow(() -> new UserDoNotExist("User do not exist"));
  }
}
