package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.model.CityAvaliation;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityAvaliationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("avaliacao")
public class CityAvaliationController {

    @Autowired
    private CityAvaliationRepository cityAvaliationRepository;

    @GetMapping
    public List<CityAvaliation> showList() {
        return cityAvaliationRepository.findAll();
    }

    @GetMapping("{code}")
    public CityAvaliation search(@PathVariable int code) {
        return cityAvaliationRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Avaliation não encontrada"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CityAvaliation create(@Valid @RequestBody CityAvaliation avaliation) {
        return cityAvaliationRepository.save(avaliation);
    }

    @PutMapping("{code}")
    public CityAvaliation update(@Valid @RequestBody CityAvaliation avaliation, @PathVariable int code) {
        avaliation.setCode(code);
        return cityAvaliationRepository.save(avaliation);
    }

    @DeleteMapping("{code}")
    public void delete(@PathVariable int code) {
        if (!cityAvaliationRepository.existsById(code)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliation não encontrada");
        }
        cityAvaliationRepository.deleteById(code);
    }
}
