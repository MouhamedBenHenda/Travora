package org.example.travora.controller;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.SignUpRequest;
import org.example.travora.dto.response.UserResponse;
import org.example.travora.model.User;
import org.example.travora.service.interfaceS.UserInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserInterface userInterface;


    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable String userId) {
        return userInterface.getUserById(userId);
    }


    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userInterface.getAllUsers();
    }

    @PutMapping("/{userId}")
    public User updateUser(
            @PathVariable String userId,
            @RequestBody SignUpRequest signUpRequest) {

        return userInterface.updateUser(userId, signUpRequest);
    }


}
