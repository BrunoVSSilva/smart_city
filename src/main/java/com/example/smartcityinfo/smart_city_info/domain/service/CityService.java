package com.example.smartcityinfo.smart_city_info.domain.service;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.model.City;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City getCity(int id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));
    }
}
