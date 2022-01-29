package com.example.barnpjeclient.controller;

import com.example.barnpjeclient.model.ClientRegisterRequest;
import com.example.barnpjeclient.model.ClientRegisterResponse;
import com.example.barnpjeclient.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @PostMapping("/register")
  public ResponseEntity<ClientRegisterResponse> registerClient(
      @RequestBody ClientRegisterRequest clientRegisterRequest) {
    return clientService.register(clientRegisterRequest);
  }

}
