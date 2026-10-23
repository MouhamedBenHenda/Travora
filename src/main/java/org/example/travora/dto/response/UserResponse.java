package org.example.travora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.model.Role;
import org.example.travora.model.UserMessageStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String id;
    private String mail;
    private String firstname;
    private String lastName;
    private Long age;
    private Role role;
    private UserMessageStatus userMessageStatus;
    private LocalDateTime lastSeen;
    private boolean useronline;
}
