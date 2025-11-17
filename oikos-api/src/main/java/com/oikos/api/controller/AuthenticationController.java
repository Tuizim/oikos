package com.oikos.api.controller;

import com.oikos.api.dto.authentication.AuthenticationDto;
import com.oikos.api.dto.authentication.LoginResponseDto;
import com.oikos.api.dto.authentication.RegisterDto;
import com.oikos.api.entity.User;
import com.oikos.api.exceptions.OikosErrorCatalog;
import com.oikos.api.exceptions.OikosException;
import com.oikos.api.infra.security.TokenService;
import com.oikos.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(),data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto dto){
        if(userService.findByUsername(dto.username()) != null){
            throw new RuntimeException(
                new OikosException(OikosErrorCatalog.USUARIO_LOGIN_JA_REGISTRADO)
            );
        }
        userService.createUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/isActive")
    public ResponseEntity isActive(){
        return ResponseEntity.ok().build();
    }
}
