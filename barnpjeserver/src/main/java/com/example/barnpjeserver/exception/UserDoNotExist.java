package com.example.barnpjeserver.exception;

public class UserDoNotExist extends RuntimeException {

  public UserDoNotExist(String message) {
    super(message);
  }
}

