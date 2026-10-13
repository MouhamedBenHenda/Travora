package org.example.travora.service.securityImpl;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.SignUpRequest;
import org.example.travora.model.User;
import org.example.travora.repository.UserRepository;
import org.example.travora.service.interfaceS.UserInterface;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserInterface {
    private final UserRepository userRepository ;
    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public User updateUser(String userId, SignUpRequest signUpRequest) {
        return null;
    }


    @Override
    public List<User> gettAllUser() {
        return null;
    }

    @Override
    public UserDetailsService userDetailsService() {

        return username -> {

            User user = userRepository.findByMail(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    "User not found with mail: " + username
                            )
                    );

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getMail())
                    .password(user.getPassword())
                    .authorities("USER")
                    .build();
        };
    }
}
