package com.oikos.api.services;

import com.oikos.api.dto.residence.ResidenceErrorCatalog;
import com.oikos.api.entity.City;
import com.oikos.api.exceptions.OikosErrorCatalog;
import com.oikos.api.exceptions.OikosException;
import com.oikos.api.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    CityRepository cityRepository;

    public City findCityById(Long id){
        return cityRepository.findById(id).orElseThrow(
            ()-> new OikosException(ResidenceErrorCatalog.CITY_NOT_FOUND)
        );
    }

}
