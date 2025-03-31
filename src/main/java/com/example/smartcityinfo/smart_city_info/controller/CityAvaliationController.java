package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.model.CityAvaliation;
import com.example.smartcityinfo.smart_city_info.service.CityAvaliationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("avaliacao")
public class CityAvaliationController {

    @Autowired
    private CityAvaliationService cityAvaliationService;

    @GetMapping
    public List<CityAvaliation> showList() {
        return cityAvaliationService.getAllAvaliation();
    }

    @GetMapping("{code}")
    public CityAvaliation search(@PathVariable int code) {
        return cityAvaliationService.getAvaliationByCode(code);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CityAvaliation create(@Valid @RequestBody CityAvaliation avaliation) {
        return cityAvaliationService.createAvaliation(avaliation);
    }

    @PutMapping("{code}")
    public CityAvaliation update(@Valid @RequestBody CityAvaliation avaliation, @PathVariable int code) {
        return cityAvaliationService.updateAvaliation(avaliation, code);
    }

    @DeleteMapping("{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int code) {
        cityAvaliationService.deleteAvaliation(code);
    }
}
