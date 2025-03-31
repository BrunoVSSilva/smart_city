package com.example.smartcityinfo.smart_city_info.service;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.model.CityAvaliation;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityAvaliationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CityAvaliationServiceTest {

    @Mock
    private CityAvaliationRepository cityAvaliationRepository;

    @InjectMocks
    private CityAvaliationService cityAvaliationService;

    private CityAvaliation cityAvaliation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cityAvaliation = new CityAvaliation();
        cityAvaliation.setCode(1);
        cityAvaliation.setSchoolAvaliation("A");
        cityAvaliation.setSecurityAvaliation("B");
        cityAvaliation.setHealthAvaliation("C");
    }

    @Test
    public void shouldGetAvaliationByCode() {
        when(cityAvaliationRepository.findById(1)).thenReturn(Optional.of(cityAvaliation));

        CityAvaliation foundAvaliation = cityAvaliationService.getAvaliationByCode(1);
        assertEquals(1, foundAvaliation.getCode());
    }

    @Test
    public void shouldThrowExceptionWhenAvaliationNotFound() {
        when(cityAvaliationRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> cityAvaliationService.getAvaliationByCode(1));
    }

    @Test
    public void shouldCreateAvaliation() {
        when(cityAvaliationRepository.save(any(CityAvaliation.class))).thenReturn(cityAvaliation);

        CityAvaliation createdAvaliation = cityAvaliationService.createAvaliation(cityAvaliation);
        assertNotNull(createdAvaliation);
        assertEquals(1, createdAvaliation.getCode());
    }

    @Test
    public void shouldUpdateAvaliation() {
        when(cityAvaliationRepository.existsById(1)).thenReturn(true);
        when(cityAvaliationRepository.save(any(CityAvaliation.class))).thenReturn(cityAvaliation);

        CityAvaliation updatedAvaliation = cityAvaliationService.updateAvaliation(cityAvaliation, 1);
        assertNotNull(updatedAvaliation);
        assertEquals(1, updatedAvaliation.getCode());
    }

    @Test
    public void shouldDeleteAvaliation() {
        when(cityAvaliationRepository.existsById(1)).thenReturn(true);
        doNothing().when(cityAvaliationRepository).deleteById(1);

        cityAvaliationService.deleteAvaliation(1);

        verify(cityAvaliationRepository, times(1)).deleteById(1);
    }

    @Test
    public void shouldThrowExceptionWhenDeletingNonExistentAvaliation() {
        when(cityAvaliationRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> cityAvaliationService.deleteAvaliation(1));
    }
}
