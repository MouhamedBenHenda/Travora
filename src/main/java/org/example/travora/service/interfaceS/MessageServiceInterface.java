package org.example.travora.service.interfaceS;

import org.example.travora.dto.request.MessageRequest;
import org.example.travora.model.Message;

public interface MessageServiceInterface {

    public Message saveMessageToGroup(String groupId , MessageRequest messageDto) ;

}
