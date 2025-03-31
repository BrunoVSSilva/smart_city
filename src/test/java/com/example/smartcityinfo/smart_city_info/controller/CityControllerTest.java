package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.exception.GlobalExceptionHandler;
import com.example.smartcityinfo.smart_city_info.domain.model.City;
import com.example.smartcityinfo.smart_city_info.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CityControllerTest {

    @Mock
    private CityService cityService;

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
    public void shouldGetAllCities() throws Exception {
        City city1 = createCity(1, "City 1");
        City city2 = createCity(2, "City 2");

        when(cityService.getAllCities()).thenReturn(Arrays.asList(city1, city2));

        mockMvc.perform(get("/cidade"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value(1))
                .andExpect(jsonPath("$[1].code").value(2));
    }

    @Test
    public void shouldGetCity() throws Exception {
        City city = createCity(1, "City 1");

        when(cityService.getCityByCode(1)).thenReturn(city);

        mockMvc.perform(get("/cidade/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.name").value("City 1"));
    }

    @Test
    public void shouldGetCityNotFound() throws Exception {
        when(cityService.getCityByCode(1)).thenThrow(new EntityNotFoundException("Cidade com código 1 não encontrada"));

        mockMvc.perform(get("/cidade/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Cidade com código 1 não encontrada"));
    }

    @Test
    public void shouldCreateCity() throws Exception {
        City city = createCity(1, "City 1");
        city.setAbout("Informações sobre City 1");

        when(cityService.createCity(any(City.class))).thenReturn(city);

        mockMvc.perform(post("/cidade")
                        .contentType("application/json")
                        .content("{\"name\": \"City 1\", \"about\": \"Informações sobre City 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.name").value("City 1"))
                .andExpect(jsonPath("$.about").value("Informações sobre City 1"));
    }

    @Test
    public void shouldVerifyCreateCityInvalid() throws Exception {
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
    public void shouldUpdateCity() throws Exception {
        City city = createCity(1, "Cidade Atualizada");
        city.setAbout("Informações sobre Cidade Atualizada");

        when(cityService.updateCity(any(City.class), eq(1))).thenReturn(city);

        mockMvc.perform(put("/cidade/1")
                        .contentType("application/json")
                        .content("{\"name\": \"Cidade Atualizada\", \"about\": \"Informações sobre Cidade Atualizada\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.name").value("Cidade Atualizada"))
                .andExpect(jsonPath("$.about").value("Informações sobre Cidade Atualizada"));
    }

    @Test
    public void shouldDeleteCity() throws Exception {
        doNothing().when(cityService).deleteCity(1);

        mockMvc.perform(delete("/cidade/1"))
                .andExpect(status().isNoContent());

        verify(cityService, times(1)).deleteCity(1);
    }

    @Test
    public void shouldDeleteCityNotFound() throws Exception {
        doThrow(new EntityNotFoundException("Cidade com código 1 não encontrada")).when(cityService).deleteCity(1);

        mockMvc.perform(delete("/cidade/1"))
                .andExpect(status().isNotFound())  // Espera o status 404
                .andExpect(jsonPath("$.message").value("Cidade com código 1 não encontrada"));
    }
}
