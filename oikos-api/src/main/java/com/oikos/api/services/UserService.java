package com.oikos.api.services;

import java.util.UUID;

import com.oikos.api.dto.authentication.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oikos.api.dto.user.UserConverter;
import com.oikos.api.dto.user.UserResponseDTO;
import com.oikos.api.entity.User;
import com.oikos.api.exceptions.OikosErrorCatalog;
import com.oikos.api.exceptions.OikosException;
import com.oikos.api.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    private final UserConverter userConverter = new UserConverter();

    public UserResponseDTO findDtoByUuid(UUID uuid){
        return userConverter.entityToResponseDto(findByUuid(uuid));
    }

    private User findByUuid(UUID uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(
            ()-> new OikosException(OikosErrorCatalog.USUARIO_NAO_ENCONTRADO)
        );
    }

    public void deleteByUuid(UUID uuid){
        if(userRepository.existsByUuid(uuid)){
            userRepository.removeByUuid(uuid);
            return;
        }
        throw new RuntimeException(
            new OikosException(OikosErrorCatalog.USUARIO_NAO_ENCONTRADO)
        );
    }

    public void createUser(RegisterDto dto){
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = User.builder()
                .username(dto.username())
                .password(encryptedPassword)
                .phone(dto.phone())
                .name(dto.name())
                .email(dto.email())
                .role(dto.role())
                .build();
        newUser.setPassword(encryptedPassword);
        this.userRepository.save(newUser);
    }

    public UserDetails findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
