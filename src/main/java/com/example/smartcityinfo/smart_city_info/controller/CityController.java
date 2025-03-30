package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.model.City;
import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidade")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping("/{code}")
    public City getCity(@PathVariable int code) {
        return cityRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Cidade com código " + code + " não encontrada"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@Valid @RequestBody City city) {
        return cityRepository.save(city);
    }

    @PutMapping("/{code}")
    public City updateCity(@Valid @RequestBody City city, @PathVariable int code) {
        city.setCode(code);
        return cityRepository.save(city);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable int code) {
        if (!cityRepository.existsById(code)) {
            throw new EntityNotFoundException("Cidade com código " + code + " não encontrada");
        }
        cityRepository.deleteById(code);
    }

}