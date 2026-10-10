package org.example.travora.Mapper;

import org.example.travora.dto.response.ChatGroupResponse;
import org.example.travora.model.ChatGroup;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ChatGroupMapper {
    public ChatGroupResponse fromChatGroup(ChatGroup chatGroup) {
        ChatGroupResponse response = new ChatGroupResponse() ;
        response.setId(chatGroup.getId());
        response.setName(chatGroup.getName());
        response.setDescription(chatGroup.getDescription()) ;
        response.setUserCreateChatId(chatGroup.getUserCreateChatId());
        response.setMemberId(chatGroup.getMemberId());

        response.setMessageIds(chatGroup.getMessages() == null ? Collections.emptyList() : chatGroup.getMessages().stream().map(message -> message.getId()).collect(Collectors.toList()));


        return  response ;

    }

}
