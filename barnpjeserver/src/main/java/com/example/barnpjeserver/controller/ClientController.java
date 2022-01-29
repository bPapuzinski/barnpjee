package com.example.barnpjeserver.controller;

import com.example.barnpjeserver.model.client.ClientRegisterRequest;
import com.example.barnpjeserver.model.client.ClientRegisterResponse;
import com.example.barnpjeserver.service.ClientService;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientController {
  private final ClientService clientService;

  @PostMapping("/client/register")
  public ResponseEntity<ClientRegisterResponse> registerClient(@RequestBody ClientRegisterRequest clientRegisterRequest) {
    return new ResponseEntity<>(clientService.registerClient(clientRegisterRequest), HttpStatus.OK);
  }
}
