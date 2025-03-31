package com.example.smartcityinfo.smart_city_info.service;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.model.CityAvaliation;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityAvaliationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityAvaliationService {

    @Autowired
    private CityAvaliationRepository cityAvaliationRepository;

    public CityAvaliation getAvaliationByCode(int code) {
        return cityAvaliationRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));
    }

    public CityAvaliation createAvaliation(CityAvaliation avaliation) {
        return cityAvaliationRepository.save(avaliation);
    }

    public CityAvaliation updateAvaliation(CityAvaliation avaliation, int code) {
        avaliation.setCode(code);
        return cityAvaliationRepository.save(avaliation);
    }

    public void deleteAvaliation(int code) {
        if (!cityAvaliationRepository.existsById(code)) {
            throw new EntityNotFoundException("Avaliação não encontrada");
        }
        cityAvaliationRepository.deleteById(code);
    }

    public List<CityAvaliation> getAllAvaliation() {
        return cityAvaliationRepository.findAll();
    }
}