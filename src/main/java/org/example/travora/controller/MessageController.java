package org.example.travora.controller;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.MessageRequest;
import org.example.travora.model.Message;
import org.example.travora.service.MessageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/message")
@RequiredArgsConstructor
public class MessageController {
private  final MessageServiceImpl messageService ;

    @PostMapping("/{groupId}/messages")
    public ResponseEntity<Message> saveMessageToGroup(
            @PathVariable String groupId,
            @RequestBody MessageRequest messageRequest) {

        Message message = messageService.saveMessageToGroup(
                groupId,
                messageRequest
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
