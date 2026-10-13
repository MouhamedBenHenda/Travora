package org.example.travora.service.securityImpl;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.SignUpRequest;
import org.example.travora.model.User;
import org.example.travora.repository.UserRepository;
import org.example.travora.service.interfaceS.UserInterface;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserInterface {
    private final UserRepository userRepository ;
    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + userId)
                );    }

    @Override
    public User updateUser(String userId, SignUpRequest signUpRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + userId)
                );

        user.setFirstname(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setMail(signUpRequest.getEmail());

        return userRepository.save(user);
    }

    @Override
    public List<User> gettAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByMail(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + username)
                );
    }
}
