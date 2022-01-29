export interface LoginRequest {
  clientPublicName: string;
  clientURL: string;
  messageType: string;
}

export interface LoginResponse {
  message: string;
}
