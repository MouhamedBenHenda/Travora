package org.example.travora.service;

import lombok.RequiredArgsConstructor;
import org.example.travora.Mapper.ChatGroupMapper;
import org.example.travora.dto.request.ChatGroupRequest;
import org.example.travora.dto.response.ChatGroupResponse;
import org.example.travora.model.ChatGroup;
import org.example.travora.model.Message;
import org.example.travora.model.User;
import org.example.travora.repository.ChatGroupRepository;
import org.example.travora.repository.MessageRepository;
import org.example.travora.repository.UserRepository;
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
    private final ChatGroupMapper chatGroupMapper ;
    private final UserRepository userRepository ;

    @Override
    public ChatGroup createChatGroupe(ChatGroupRequest chatGroupRequest , String createrId) {
        User user = userRepository.findById(createrId).orElseThrow(() -> new RuntimeException( "user not found")) ;

        ChatGroup chatGroup = new ChatGroup() ;
        chatGroup.setDescription(chatGroupRequest.getDescription());
        chatGroup.setUserCreateChatId(createrId);
        chatGroup.setName(chatGroupRequest.getName());
        chatGroup.setMemberId(
                chatGroupRequest.getMemberId() != null
                        ? new ArrayList<>(chatGroupRequest.getMemberId())
                        : new ArrayList<>()
        );
        chatGroup.setUser(user);
        chatGroup.setMessages(new ArrayList<>());
        chatGroup.setCreLocalDate(this.convertToLocalDateTimeViaMilisecond(new Date()));
        return chatGroupRepository.save(chatGroup);
    }

    @Override
    public List<ChatGroupResponse> getAllChatGroups() {


        return chatGroupRepository.findAll()
                .stream()
                .map(chatGroupMapper::fromChatGroup)
                .toList();
    }

    @Override
    public ChatGroupResponse getChatGroupById(String groupId) {
        ChatGroup chatGroup = chatGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("ChatGroup not found: " + groupId)
                );

        return chatGroupMapper.fromChatGroup(chatGroup);
    }

    @Override
    public ChatGroup updateChatGroup(String groupId, ChatGroupResponse chatGroupResponse) {
        return null;
    }

    @Override
    public void deleteChatGroup(String groupId) {
        ChatGroup chatGroup = findChatGroupOrThrow(groupId);

        chatGroupRepository.delete(chatGroup);
    }

    @Override
    public ChatGroup addMemberToGroup(String groupId, String memberId) {

        ChatGroup chatGroup = chatGroupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Chat group not found" + groupId)) ;
        if (memberId == null || memberId.isBlank()) {
            throw new RuntimeException("member id cannot empty") ;

        }
        if (chatGroup.getMemberId() == null) {
            chatGroup.setMemberId(new ArrayList<>());
        }
        if (!chatGroup.getMemberId().contains(memberId)){
            chatGroup.getMemberId().add(memberId) ;

        }
        return chatGroupRepository.save(chatGroup);
    }

    @Override
    public ChatGroup removeMemberFromGroup(String groupId, String memberId) {

        ChatGroup chatGroup = chatGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Chat group not found: " + groupId)
                );

        if (memberId == null || memberId.isBlank()) {
            throw new RuntimeException("Member ID cannot be empty");
        }

        if (chatGroup.getMemberId() == null ||
                !chatGroup.getMemberId().contains(memberId)) {
            throw new RuntimeException("Member not found in this chat group");
        }

        chatGroup.getMemberId().remove(memberId);

        return chatGroupRepository.save(chatGroup);
    }

    @Override
    public List<Message> getMessagesFromGroup(String groupId) {
        findChatGroupOrThrow(groupId) ;
        return messageRepository.findByChatGroup_IdOrderByCreLocalDateAsc(groupId) ;

    }

    public LocalDateTime convertToLocalDateTimeViaMilisecond (Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime() ;
    }

    private ChatGroup findChatGroupOrThrow(String groupId){
        return  chatGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Chat group not found with ID: " + groupId
                        )
                );
    }
}
