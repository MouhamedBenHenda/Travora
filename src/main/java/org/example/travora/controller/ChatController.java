package org.example.travora.controller;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.ChatGroupRequest;
import org.example.travora.model.ChatGroup;
import org.example.travora.service.interfaceS.ChatGroupInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private  final ChatGroupInterface chatGroupService ;
    @PostMapping
    public ResponseEntity<ChatGroup> createChatGroupe(
            @RequestBody ChatGroupRequest chatGroupRequest) {

        ChatGroup chatGroup =
                chatGroupService.createChatGroupe(chatGroupRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(chatGroup);
    }
}
