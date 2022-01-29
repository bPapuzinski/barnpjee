package com.example.barnpjeclient.service;

import com.example.barnpjeclient.model.ClientRegisterRequest;
import com.example.barnpjeclient.model.ClientRegisterResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ClientService {
  private final RestTemplate restTemplate;

  public ResponseEntity<ClientRegisterResponse> register(ClientRegisterRequest clientRegisterRequest) {
    String url = "http://localhost:8081/client/register";
    return restTemplate.postForEntity(url, clientRegisterRequest, ClientRegisterResponse.class);
  }

}
