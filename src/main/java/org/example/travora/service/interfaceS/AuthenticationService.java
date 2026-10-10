package org.example.travora.service.interfaceS;

import org.example.travora.dto.request.SignUpRequest;
import org.example.travora.dto.request.SingninRequest;
import org.example.travora.dto.response.JwtAuthenticationResponse;
import org.example.travora.model.User;

public interface AuthenticationService {

    JwtAuthenticationResponse SignUp(SignUpRequest request);
    JwtAuthenticationResponse SignIn(SingninRequest request);

}
