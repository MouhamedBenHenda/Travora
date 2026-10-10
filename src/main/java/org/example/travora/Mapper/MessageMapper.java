package org.example.travora.Mapper;

import org.example.travora.dto.request.MessageRequest;
import org.example.travora.dto.response.MessageResponse;
import org.example.travora.model.FileUtils;
import org.example.travora.model.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {

public MessageResponse toMessageResponse (Message message) {
    return MessageResponse.builder().
            id(message.getId()).
            content(message.getContent())
            .senderId(message.getSenderId())
            .messageType(message.getMessageType())
            .createAt(message.getCreLocalDate())
            .media(FileUtils.redFileFromlocation(message.getMediaFilePath())).build() ;

    
}

}
