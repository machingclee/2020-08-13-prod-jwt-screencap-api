package com.screencap.dictionary.controllers;

import com.screencap.dictionary.models.AuthRequest;
import com.screencap.dictionary.models.AuthResponse;
import com.screencap.dictionary.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest reqBody) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    reqBody.getUsername(),
                    reqBody.getPassword()
                )
            );
        } catch (Exception e) {
            throw new Exception();
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(reqBody.getUsername());
        String token = jwtUtil.createToken("username", userDetails.getUsername());
        AuthResponse response = new AuthResponse(token);

        return ResponseEntity.ok(response);
    }

}
