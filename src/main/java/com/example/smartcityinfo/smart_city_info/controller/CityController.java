package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.model.City;
import com.example.smartcityinfo.smart_city_info.service.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidade")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{code}")
    public City getCity(@PathVariable int code) {
        return cityService.getCityByCode(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@Valid @RequestBody City city) {
        return cityService.createCity(city);
    }

    @PutMapping("/{code}")
    public City updateCity(@Valid @RequestBody City city, @PathVariable int code) {
        return cityService.updateCity(city, code);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable int code) {
        cityService.deleteCity(code);
    }
}
