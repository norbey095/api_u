package com.emazon.api_user.infraestructure.output.adapter.securityconfig;

import com.emazon.api_user.application.authentication.AuthenticationRequest;
import com.emazon.api_user.application.authentication.AuthenticationResponse;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration.JwtService;
import com.emazon.api_user.infraestructure.output.reposiroty.IUserRepository;
import com.emazon.api_user.infraestructure.output.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        var userName = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(Constants.USER_NOT_FOUND))
                .getEmail();

        var jwtToken = jwtService.generateToken(userName,userDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
