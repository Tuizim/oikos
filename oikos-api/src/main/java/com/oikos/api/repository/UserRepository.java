package com.oikos.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.oikos.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUuid(UUID uuid);
    UserDetails findByLogin(String login);
    boolean existsByUuid(UUID uuid);
    void removeByUuid(UUID uuid);
}
