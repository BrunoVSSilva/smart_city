package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.model.CityAvaliation;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityAvaliationRepository;
import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));
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
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Adicionando esta linha
    public void delete(@PathVariable int code) {
        if (!cityAvaliationRepository.existsById(code)) {
            throw new EntityNotFoundException("Avaliação não encontrada");
        }
        cityAvaliationRepository.deleteById(code);
    }
}