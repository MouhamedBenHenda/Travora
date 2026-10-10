package org.example.travora.service;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.MessageRequest;
import org.example.travora.model.MessageState;
import org.example.travora.service.interfaceS.MessageServiceInterface;
import org.example.travora.model.ChatGroup;
import org.example.travora.model.Message;
import org.example.travora.repository.ChatGroupRepository;
import org.example.travora.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageServiceInterface {
    private final ChatGroupRepository chatGroupRepository ;
    private final MessageRepository messageRepository ;

    @Override
    public Message saveMessageToGroup(String groupId, MessageRequest messageRequest) {
        ChatGroup chatGroup = chatGroupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("chat group not found")) ;

            Message message1 = new Message() ;

            message1.setContent(messageRequest.getContent());
            message1.setSenderId(messageRequest.getSenderId());
            message1.setMessageType(messageRequest.getMessageType());
            message1.setMessageState(MessageState.SENT);
            message1.setCreLocalDate(this.convertToLocalDateTimeViaMilisecond(new Date()));
            message1.setLastDate(this.convertToLocalDateTimeViaMilisecond(new Date()));
            message1.setChatGroup(chatGroup);

//notification



        Message savedMessage = messageRepository.save(message1);

        return savedMessage;




    }


    public LocalDateTime convertToLocalDateTimeViaMilisecond (Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime() ;
    }
}
