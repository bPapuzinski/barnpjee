package com.example.barnpjeserver.controller;

import com.example.barnpjeserver.exception.DuplicateClientNameException;
import com.example.barnpjeserver.exception.MessageSendUserToHimself;
import com.example.barnpjeserver.exception.UserDoNotExist;
import com.example.barnpjeserver.model.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

  @ExceptionHandler(DuplicateClientNameException.class)
  protected ResponseEntity<Object> handleDuplicateClientNameException(
      DuplicateClientNameException ex, WebRequest request) {
    return handleExceptionInternal(ex, new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(MessageSendUserToHimself.class)
  protected ResponseEntity<Object> handleMessageSendUserToHimself(
      MessageSendUserToHimself ex, WebRequest request) {
    return handleExceptionInternal(ex, new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(UserDoNotExist.class)
  protected ResponseEntity<Object> handleUserDoNotExist(
      UserDoNotExist ex, WebRequest request) {
    return handleExceptionInternal(ex, new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
