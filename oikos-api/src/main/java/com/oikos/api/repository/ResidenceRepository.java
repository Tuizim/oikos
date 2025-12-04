package com.oikos.api.repository;

import com.oikos.api.entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence,Long> {
    boolean existsByOwnerIdAndName(UUID ownerId, String name);
}
