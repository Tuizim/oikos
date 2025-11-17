package com.oikos.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import com.oikos.api.dto.user.UserResponseDTO;
import com.oikos.api.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uuid}")
    public UserResponseDTO findByUuid(@PathVariable UUID uuid) {
        return userService.findDtoByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity deleteByUuid(@PathVariable UUID uuid){
        userService.deleteByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}
