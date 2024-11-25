package com.med.voll.api.general.infra.security;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> Authenticate(@Valid  @RequestBody DataAutentication dataAutentication) {

        Authentication TOKEN = new UsernamePasswordAuthenticationToken(dataAutentication.username(), dataAutentication.password());

        authenticationManager.authenticate(TOKEN);

        return ResponseEntity.ok(TOKEN);
    }
}