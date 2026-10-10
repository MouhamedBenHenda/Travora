package org.example.travora.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.model.MessageType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
private String id ;
private String Content ;
private String senderId ;
private String chatGroupId ;
private MessageType messageType ;
}
