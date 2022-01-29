package com.example.barnpjeserver.model.message;

import com.example.barnpjeserver.model.MessageTypeEnum;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MessageServerRequest implements Serializable {
  private String fromClientName;
  private String toClientName;
  private String message;
  private String messageType;
}
