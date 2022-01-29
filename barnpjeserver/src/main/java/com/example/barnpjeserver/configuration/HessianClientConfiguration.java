package com.example.barnpjeserver.configuration;

import com.example.barnpjeserver.service.MessageClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class HessianClientConfiguration {

  @Bean
  @Primary
  public HessianProxyFactoryBean hessianInvoker() {
    HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
    invoker.setServiceUrl("http://localhost:8080/hellohessian");
    invoker.setServiceInterface(MessageClientService.class);
    return invoker;
  }

}
