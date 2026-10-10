package org.example.travora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGroupResponse {

    private String id;

    private String name;

    private String description;

    private String userCreateChatId;

    private List<String> memberId;

    private List<String> messageIds;
    private LocalDateTime createAt ;
}
