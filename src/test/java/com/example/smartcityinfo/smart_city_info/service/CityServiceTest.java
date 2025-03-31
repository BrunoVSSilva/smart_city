package com.example.smartcityinfo.smart_city_info.service;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.model.City;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    private City city;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        city = new City();
        city.setCode(1);
        city.setName("City 1");
    }

    @Test
    public void shouldGetCityByCode() {
        when(cityRepository.findById(1)).thenReturn(Optional.of(city));

        City foundCity = cityService.getCityByCode(1);
        assertEquals(1, foundCity.getCode());
        assertEquals("City 1", foundCity.getName());
    }

    @Test
    public void shouldThrowExceptionWhenCityNotFound() {
        when(cityRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> cityService.getCityByCode(1));
    }

    @Test
    public void shouldCreateCity() {
        when(cityRepository.save(any(City.class))).thenReturn(city);

        City createdCity = cityService.createCity(city);
        assertNotNull(createdCity);
        assertEquals(1, createdCity.getCode());
    }

    @Test
    public void shouldUpdateCity() {
        when(cityRepository.existsById(1)).thenReturn(true);
        when(cityRepository.save(any(City.class))).thenReturn(city);

        City updatedCity = cityService.updateCity(city, 1);
        assertNotNull(updatedCity);
        assertEquals(1, updatedCity.getCode());
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNonExistentCity() {
        when(cityRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> cityService.updateCity(city, 1));
    }

    @Test
    public void shouldDeleteCity() {
        when(cityRepository.existsById(1)).thenReturn(true);
        doNothing().when(cityRepository).deleteById(1);

        cityService.deleteCity(1);

        verify(cityRepository, times(1)).deleteById(1);
    }

    @Test
    public void shouldThrowExceptionWhenDeletingNonExistentCity() {
        when(cityRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> cityService.deleteCity(1));
    }
}
