package org.example.travora.service;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.ChatGroupRequest;
import org.example.travora.dto.response.ChatGroupResponse;
import org.example.travora.model.ChatGroup;
import org.example.travora.model.Message;
import org.example.travora.repository.ChatGroupRepository;
import org.example.travora.repository.MessageRepository;
import org.example.travora.service.interfaceS.ChatGroupInterface;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatGroupeServiceImpl  implements ChatGroupInterface {
    private final ChatGroupRepository chatGroupRepository ;
    private final MessageRepository messageRepository ;


    @Override
    public ChatGroup createChatGroupe(ChatGroupRequest chatGroupRequest) {

        ChatGroup chatGroup = new ChatGroup() ;
        chatGroup.setDescription(chatGroupRequest.getDescription());
        chatGroup.setUserCreateChatId(chatGroupRequest.getUserCreateChatId());
        chatGroup.setName(chatGroupRequest.getName());
        chatGroup.setMemberId(
                chatGroupRequest.getMemberId() != null
                        ? new ArrayList<>(chatGroupRequest.getMemberId())
                        : new ArrayList<>()
        );
        chatGroup.setMessages(new ArrayList<>());
        chatGroup.setCreLocalDate(this.convertToLocalDateTimeViaMilisecond(new Date()));
        return chatGroupRepository.save(chatGroup);
    }

    @Override
    public List<ChatGroup> getAllChatGroups() {
        return null;
    }

    @Override
    public ChatGroup getChatGroupById(String groupId) {
        return null;
    }

    @Override
    public ChatGroup updateChatGroup(String groupId, ChatGroupResponse chatGroupResponse) {
        return null;
    }

    @Override
    public void deleteChatGroup(String groupId) {

    }

    @Override
    public ChatGroup addMemberToGroup(String groupId, String memberId) {
        return null;
    }

    @Override
    public ChatGroup removeMemberFromGroup(String groupId, String memberId) {
        return null;
    }

    @Override
    public List<Message> getMessagesFromGroup(String groupId) {
        return null;
    }

    public LocalDateTime convertToLocalDateTimeViaMilisecond (Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime() ;
    }
}
