package com.oikos.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    private UserConverter userConverter = new UserConverter();

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
}
