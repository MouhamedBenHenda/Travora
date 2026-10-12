package org.example.travora.controller;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.ChatGroupRequest;
import org.example.travora.dto.response.ChatGroupResponse;
import org.example.travora.model.ChatGroup;
import org.example.travora.model.Message;
import org.example.travora.service.interfaceS.ChatGroupInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private  final ChatGroupInterface chatGroupService ;
    @PostMapping ("/{createrId}")
    public ResponseEntity<ChatGroup> createChatGroupe(
            @RequestBody ChatGroupRequest chatGroupRequest , @PathVariable String createrId ) {

        ChatGroup chatGroup =
                chatGroupService.createChatGroupe(chatGroupRequest , createrId);

        return ResponseEntity.status(HttpStatus.CREATED).body(chatGroup);
    }
    @GetMapping
    public ResponseEntity<List<ChatGroupResponse>> getAllChatGroups() {

        return ResponseEntity.ok(chatGroupService.getAllChatGroups());
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<ChatGroupResponse> getChatGroupById(
            @PathVariable String groupId) {

        return ResponseEntity.ok(
                chatGroupService.getChatGroupById(groupId)
        );
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<ChatGroup> updateChatGroup(
            @PathVariable String groupId,
            @RequestBody ChatGroupResponse chatGroupResponse) {

        return ResponseEntity.ok(
                chatGroupService.updateChatGroup(groupId, chatGroupResponse)
        );
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteChatGroup(
            @PathVariable String groupId) {

        chatGroupService.deleteChatGroup(groupId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{groupId}/members/{memberId}")
    public ResponseEntity<ChatGroup> addMemberToGroup(
            @PathVariable String groupId,
            @PathVariable String memberId) {

        return ResponseEntity.ok(
                chatGroupService.addMemberToGroup(groupId, memberId)
        );
    }

    @DeleteMapping("/{groupId}/members/{memberId}")
    public ResponseEntity<ChatGroup> removeMemberFromGroup(
            @PathVariable String groupId,
            @PathVariable String memberId) {

        return ResponseEntity.ok(
                chatGroupService.removeMemberFromGroup(groupId, memberId)
        );
    }
    @GetMapping("/{groupId}/messages")
    public ResponseEntity<List<Message>> getMessagesFromGroup(
            @PathVariable String groupId) {

        return ResponseEntity.ok(
                chatGroupService.getMessagesFromGroup(groupId)
        );
    }
}
