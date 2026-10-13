package org.example.travora.commen;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.SignUpRequest;
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
    public User getUser(@PathVariable String userId) {
        return userInterface.getUser(userId);
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userInterface.gettAllUser();
    }

    @PutMapping("/{userId}")
    public User updateUser(
            @PathVariable String userId,
            @RequestBody SignUpRequest signUpRequest) {

        return userInterface.updateUser(userId, signUpRequest);
    }


}
