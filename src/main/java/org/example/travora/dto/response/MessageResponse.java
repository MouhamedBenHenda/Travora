package org.example.travora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.model.MessageType;

import java.time.LocalDateTime;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {
private String id ;
private String chatId ;
private String content ;
private String senderId ;
private MessageType messageType ;
private LocalDateTime createAt ;
private byte[] media ;

}
