package com.oikos.api.controller;

import com.oikos.api.dto.residence.ResidenceCreateDTO;
import com.oikos.api.dto.residence.ResidenceResponseDTO;
import com.oikos.api.services.ResidenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/residence")
public class ResidenceController {

    @Autowired
    ResidenceService residenceService;
    @PostMapping()
    @Transactional
    public ResidenceResponseDTO create(@RequestBody @Valid ResidenceCreateDTO dto){
        return residenceService.create(dto);
    }
}
