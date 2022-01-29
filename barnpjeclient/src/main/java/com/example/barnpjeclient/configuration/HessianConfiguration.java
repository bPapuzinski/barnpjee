package com.example.barnpjeclient.configuration;

import com.example.barnpjeclient.service.MessageClientService;
import com.example.barnpjeclient.service.MessageClientServiceImpl;
import com.example.barnpjeclient.service.MessageWriteService;
import com.example.barnpjeserver.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class HessianConfiguration {

  private final RestTemplate restTemplate;
  private final MessageWriteService messageWriteService;
  private final com.example.barnpjeserver.service.MessageService messageService;

  @Bean(name = "/hellohessian")
  RemoteExporter sayHelloServiceHessian() {
    HessianServiceExporter exporter = new HessianServiceExporter();
    exporter.setService(new MessageClientServiceImpl(restTemplate, messageService, messageWriteService));
    exporter.setServiceInterface(MessageClientService.class);
    return exporter;
  }

}
