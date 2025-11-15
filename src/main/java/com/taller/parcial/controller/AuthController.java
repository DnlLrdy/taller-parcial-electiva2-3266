package com.taller.parcial.controller;

import com.taller.parcial.config.JwtUtil;
import com.taller.parcial.dto.request.LoginRequest;
import com.taller.parcial.dto.response.JwtResponse;
import com.taller.parcial.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getCorreoElectronico(),
                            request.getContraseña()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getCorreoElectronico());
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

}
