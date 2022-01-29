export interface MessageSendRequest {
  fromClientName?: string;
  toClientName: string;
  message: string;
  messageType: string;
}

export interface Message {
  fromClientName: string;
  toClientName: string;
  message: string;
}


