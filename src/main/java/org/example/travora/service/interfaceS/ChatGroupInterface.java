package org.example.travora.service.interfaceS;

import org.example.travora.dto.request.ChatGroupRequest;
import org.example.travora.dto.response.ChatGroupResponse;
import org.example.travora.model.ChatGroup;
import org.example.travora.model.Message;

import java.util.List;

public interface ChatGroupInterface {
    ChatGroup createChatGroupe(ChatGroupRequest chatGroupRequest);

    List<ChatGroup> getAllChatGroups();

    ChatGroup getChatGroupById(String groupId);

    ChatGroup updateChatGroup(String groupId, ChatGroupResponse chatGroupResponse);

    void deleteChatGroup(String groupId);

    ChatGroup addMemberToGroup(String groupId, String memberId);

    ChatGroup removeMemberFromGroup(String groupId, String memberId);

    List<Message> getMessagesFromGroup(String groupId);
}
