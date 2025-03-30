package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.exception.GlobalExceptionHandler;
import com.example.smartcityinfo.smart_city_info.domain.model.City;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CityControllerTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityController cityController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cityController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    private City createCity(int code, String name) {
        City city = new City();
        city.setCode(code);
        city.setName(name);
        return city;
    }

    @Test
    public void testGetAllCities() throws Exception {
        City city1 = createCity(1, "City 1");
        City city2 = createCity(2, "City 2");

        when(cityRepository.findAll()).thenReturn(Arrays.asList(city1, city2));

        mockMvc.perform(get("/cidade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value(1))
                .andExpect(jsonPath("$[1].code").value(2));
    }

    @Test
    public void testGetCity() throws Exception {
        City city = createCity(1, "City 1");

        when(cityRepository.findById(1)).thenReturn(Optional.of(city));

        mockMvc.perform(get("/cidade/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.name").value("City 1"));
    }

    @Test
    public void testGetCityNotFound() throws Exception {
        when(cityRepository.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/cidade/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Cidade com código 1 não encontrada"));
    }

    @Test
    public void testCreateCity() throws Exception {
        City city = createCity(1, "City 1");
        city.setAbout("Informações sobre City 1");

        when(cityRepository.save(any(City.class))).thenReturn(city);

        mockMvc.perform(post("/cidade")
                        .contentType("application/json")
                        .content("{\"name\": \"City 1\", \"about\": \"Informações sobre City 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.name").value("City 1"))
                .andExpect(jsonPath("$.about").value("Informações sobre City 1"));
    }


    @Test
    public void testCreateCityInvalid() throws Exception {
        mockMvc.perform(post("/cidade")
                        .contentType("application/json")
                        .content("{\"name\": \"\", \"about\": \"Informações sobre City 1\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0]").value("name: Nome obrigatório!"));

        mockMvc.perform(post("/cidade")
                        .contentType("application/json")
                        .content("{\"name\": \"City 1\", \"about\": \"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0]").value("about: Campo 'sobre' obrigatório!"));
    }


    @Test
    public void testUpdateCity() throws Exception {
        City city = createCity(1, "Cidade Atualizada");
        city.setAbout("Informações sobre Cidade Atualizada");

        when(cityRepository.save(any(City.class))).thenReturn(city);
        when(cityRepository.findById(1)).thenReturn(Optional.of(city));

        mockMvc.perform(put("/cidade/1")
                        .contentType("application/json")
                        .content("{\"name\": \"Cidade Atualizada\", \"about\": \"Informações sobre Cidade Atualizada\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.name").value("Cidade Atualizada"))
                .andExpect(jsonPath("$.about").value("Informações sobre Cidade Atualizada"));
    }

    @Test
    public void testDeleteCity() throws Exception {
        when(cityRepository.existsById(1)).thenReturn(true);
        doNothing().when(cityRepository).deleteById(1);

        mockMvc.perform(delete("/cidade/1"))
                .andExpect(status().isNoContent());

        verify(cityRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteCityNotFound() throws Exception {
        when(cityRepository.existsById(1)).thenReturn(false);

        mockMvc.perform(delete("/cidade/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Cidade com código 1 não encontrada"));
    }
}
