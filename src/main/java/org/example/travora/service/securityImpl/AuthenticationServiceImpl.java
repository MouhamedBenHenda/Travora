
        package org.example.travora.service.securityImpl;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.SignUpRequest;
import org.example.travora.dto.request.SingninRequest;
import org.example.travora.dto.response.JwtAuthenticationResponse;
import org.example.travora.model.Role;
import org.example.travora.model.User;
import org.example.travora.repository.UserRepository;
import org.example.travora.service.interfaceS.AuthenticationService;
import org.example.travora.service.interfaceS.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public JwtAuthenticationResponse SignUp(SignUpRequest request) {

        Role role = request.getRole() != null
                ? request.getRole()
                : Role.NORMAL_ADVENTURER;

        var user = User.builder()
                .firstname(request.getFirstName())
                .lastName(request.getLastName())
                .mail(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        // Save the user first
        User savedUser = userRepository.save(user);

        // Generate JWT using the saved user
        String jwt = jwtService.generateToken(savedUser);

        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .userId(savedUser.getId())
                .role(savedUser.getRole().name())
                .build();
    }

    @Override
    public JwtAuthenticationResponse SignIn(SingninRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByMail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email")
                );

        String jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .userId(user.getId())
                .role(user.getRole().name())
                .build();
    }
}

