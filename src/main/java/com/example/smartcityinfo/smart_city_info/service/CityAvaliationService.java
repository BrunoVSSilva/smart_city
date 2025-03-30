package com.example.smartcityinfo.smart_city_info.service;

import com.example.smartcityinfo.smart_city_info.domain.model.CityAvaliation;
import com.example.smartcityinfo.smart_city_info.domain.exception.CityAvaliationNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.exception.InvalidCityAvaliationException;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityAvaliationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityAvaliationService {

    @Autowired
    private CityAvaliationRepository cityAvaliationRepository;

    public CityAvaliation findCityAvaliationByCode(int code) {
        return cityAvaliationRepository.findById(code)
                .orElseThrow(() -> new CityAvaliationNotFoundException("Avaliação da cidade não encontrada para o código " + code));
    }

    public CityAvaliation saveCityAvaliation(CityAvaliation cityAvaliation) {
        if (cityAvaliation.getSchoolAvaliation() == null || cityAvaliation.getSecurityAvaliation() == null || cityAvaliation.getHealthAvaliation() == null) {
            throw new InvalidCityAvaliationException("Todos os campos de avaliação devem ser preenchidos!");
        }

        return cityAvaliationRepository.save(cityAvaliation);
    }

    public void deleteCityAvaliation(int code) {
        if (!cityAvaliationRepository.existsById(code)) {
            throw new CityAvaliationNotFoundException("Avaliação da cidade não encontrada para exclusão.");
        }
        cityAvaliationRepository.deleteById(code);
    }
}
