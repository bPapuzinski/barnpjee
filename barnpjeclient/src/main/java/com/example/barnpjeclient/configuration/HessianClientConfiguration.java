package com.example.barnpjeclient.configuration;

import com.example.barnpjeserver.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class HessianClientConfiguration {

  @Bean
  public HessianProxyFactoryBean hessianInvoker() {
    HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
    invoker.setServiceUrl("http://localhost:8081/hellohessian");
    invoker.setServiceInterface(MessageService.class);
    return invoker;
  }

}
