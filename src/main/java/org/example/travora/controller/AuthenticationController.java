package org.example.travora.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.example.travora.dto.request.SignUpRequest;
import org.example.travora.dto.request.SingninRequest;
import org.example.travora.dto.response.JwtAuthenticationResponse;
import org.example.travora.service.interfaceS.AuthenticationService;
import org.example.travora.service.interfaceS.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:8081")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    private final JwtService jwtService ;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.SignUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SingninRequest request, HttpServletResponse response) {
        JwtAuthenticationResponse jwtResponse = authenticationService.SignIn(request);

        if (jwtResponse != null && jwtResponse.getToken() != null) {
            // Set the token in the response header

            return ResponseEntity.ok(jwtResponse);
        } else {
            return ResponseEntity.badRequest().body(jwtResponse); // Assuming jwtResponse can be null
        }
    }

}
