package com.example.barnpjeserver.configuration;

import com.example.barnpjeserver.service.ClientService;
import com.example.barnpjeserver.service.MessageClientService;
import com.example.barnpjeserver.service.MessageService;
import com.example.barnpjeserver.service.MessageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class HessianConfiguration {

  private final RestTemplate restTemplate;
  private final ClientService clientService;
  private final MessageClientService messageClientService;
  private final HessianProxyFactoryBean hessianProxyFactoryBean;

  @Bean(name = "/hellohessian")
  RemoteExporter sayHelloServiceHessian() {
    HessianServiceExporter exporter = new HessianServiceExporter();
    exporter.setService(new MessageServiceImpl(restTemplate, clientService, messageClientService, hessianProxyFactoryBean));
    exporter.setServiceInterface(MessageService.class);
    return exporter;
  }

}

