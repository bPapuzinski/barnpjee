package com.example.barnpjeserver.service;


import com.example.barnpjeserver.model.message.Message;
import com.example.barnpjeserver.model.message.MessageServerRequest;
import java.util.Map;

public interface MessageService {

  Map<String, Message> sendMessage(MessageServerRequest messageServerRequest);
}
