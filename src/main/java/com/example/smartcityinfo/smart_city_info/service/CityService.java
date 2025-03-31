package com.example.smartcityinfo.smart_city_info.service;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.model.City;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityByCode(int code) {
        return cityRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Cidade com código " + code + " não encontrada"));
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(City city, int code) {
        if (!cityRepository.existsById(code)) {
            throw new EntityNotFoundException("Cidade com código " + code + " não encontrada");
        }
        city.setCode(code);
        return cityRepository.save(city);
    }

    public void deleteCity(int code) {
        if (!cityRepository.existsById(code)) {
            throw new EntityNotFoundException("Cidade com código " + code + " não encontrada");
        }
        cityRepository.deleteById(code);
    }
}
