package org.example.travora.Mapper;

import org.example.travora.dto.response.UserResponse;
import org.example.travora.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .mail(user.getMail())
                .firstname(user.getFirstname())
                .lastName(user.getLastName())
                .age(user.getAge())
                .role(user.getRole())
                .userMessageStatus(user.getUserMessageStatus())
                .lastSeen(user.getLastSeen())
                .useronline(user.isUseronline())
                .build();
    }
}
