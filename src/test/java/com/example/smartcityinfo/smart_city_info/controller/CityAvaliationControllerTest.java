package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.model.CityAvaliation;
import com.example.smartcityinfo.smart_city_info.domain.exception.GlobalExceptionHandler;
import com.example.smartcityinfo.smart_city_info.service.CityAvaliationService;
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

public class CityAvaliationControllerTest {

    @Mock
    private CityAvaliationService cityAvaliationService;

    @InjectMocks
    private CityAvaliationController cityAvaliationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cityAvaliationController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    private CityAvaliation createAvaliation(int code, String schoolAvaliation) {
        CityAvaliation avaliation = new CityAvaliation();
        avaliation.setCode(code);
        avaliation.setSchoolAvaliation(schoolAvaliation);
        return avaliation;
    }

    @Test
    public void shouldGetAllAvaliation() throws Exception {
        CityAvaliation avaliation1 = createAvaliation(1, "A");
        CityAvaliation avaliation2 = createAvaliation(2, "B");

        when(cityAvaliationService.getAllAvaliation()).thenReturn(Arrays.asList(avaliation1, avaliation2));

        mockMvc.perform(get("/avaliacao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value(1))
                .andExpect(jsonPath("$[1].code").value(2));
    }

    @Test
    public void shouldGetAvaliation() throws Exception {
        CityAvaliation avaliation = createAvaliation(1, "A");

        when(cityAvaliationService.getAvaliationByCode(1)).thenReturn(avaliation);

        mockMvc.perform(get("/avaliacao/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.schoolAvaliation").value("A"));
    }

    @Test
    public void shouldGetAvaliationNotFound() throws Exception {
        when(cityAvaliationService.getAvaliationByCode(1)).thenThrow(new EntityNotFoundException("Avaliação não encontrada"));

        mockMvc.perform(get("/avaliacao/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Avaliação não encontrada"));
    }

    @Test
    public void shouldCreateAvaliation() throws Exception {
        CityAvaliation avaliation = createAvaliation(1, "A");

        when(cityAvaliationService.createAvaliation(any(CityAvaliation.class))).thenReturn(avaliation);

        mockMvc.perform(post("/avaliacao")
                        .contentType("application/json")
                        .content("{\"schoolAvaliation\": \"A\", \"securityAvaliation\": \"B\", \"healthAvaliation\": \"C\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.schoolAvaliation").value("A"));
    }

    @Test
    public void shouldUpdateAvaliation() throws Exception {
        CityAvaliation avaliation = createAvaliation(1, "A");

        when(cityAvaliationService.updateAvaliation(any(CityAvaliation.class), eq(1))).thenReturn(avaliation);

        mockMvc.perform(put("/avaliacao/1")
                        .contentType("application/json")
                        .content("{\"schoolAvaliation\": \"A\", \"securityAvaliation\": \"B\", \"healthAvaliation\": \"C\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.schoolAvaliation").value("A"));
    }



    @Test
    public void shouldDeleteAvaliation() throws Exception {
        doNothing().when(cityAvaliationService).deleteAvaliation(1);

        mockMvc.perform(delete("/avaliacao/1"))
                .andExpect(status().isNoContent());

        verify(cityAvaliationService, times(1)).deleteAvaliation(1);
    }

    @Test
    public void shouldDeleteAvaliationNotFound() throws Exception {
        doThrow(new EntityNotFoundException("Avaliação não encontrada")).when(cityAvaliationService).deleteAvaliation(1);

        mockMvc.perform(delete("/avaliacao/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Avaliação não encontrada"));
    }
}
