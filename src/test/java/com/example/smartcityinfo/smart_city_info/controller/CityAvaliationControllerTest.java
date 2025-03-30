package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.exception.GlobalExceptionHandler;
import com.example.smartcityinfo.smart_city_info.domain.model.CityAvaliation;
import com.example.smartcityinfo.smart_city_info.domain.repository.CityAvaliationRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CityAvaliationControllerTest {

    @Mock
    private CityAvaliationRepository cityAvaliationRepository;

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

    @Test
    public void testShowList() throws Exception {
        // Arrange
        CityAvaliation avaliation1 = new CityAvaliation();
        avaliation1.setCode(1);
        avaliation1.setSchoolAvaliation("Avaliação das escolas ok");
        avaliation1.setSecurityAvaliation("Avaliação da segurança ok");
        avaliation1.setHealthAvaliation("Avaliação da saúde ok");
        avaliation1.setCityId(1);

        CityAvaliation avaliation2 = new CityAvaliation();
        avaliation2.setCode(2);
        avaliation2.setSchoolAvaliation("Avaliação das escolas excelente");
        avaliation2.setSecurityAvaliation("Avaliação da segurança boa");
        avaliation2.setHealthAvaliation("Avaliação da saúde ruim");
        avaliation2.setCityId(2);

        when(cityAvaliationRepository.findAll()).thenReturn(Arrays.asList(avaliation1, avaliation2));

        mockMvc.perform(get("/avaliacao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value(1))
                .andExpect(jsonPath("$[1].code").value(2));
    }

    @Test
    public void testSearch() throws Exception {
        CityAvaliation avaliation = new CityAvaliation();
        avaliation.setCode(1);
        avaliation.setSchoolAvaliation("Avaliação das escolas boas");
        avaliation.setSecurityAvaliation("Avaliação da segurança boa");
        avaliation.setHealthAvaliation("Avaliação da saúde excelente");
        avaliation.setCityId(1);

        when(cityAvaliationRepository.findById(1)).thenReturn(Optional.of(avaliation));

        mockMvc.perform(get("/avaliacao/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.schoolAvaliation").value("Avaliação das escolas boas"))
                .andExpect(jsonPath("$.securityAvaliation").value("Avaliação da segurança boa"))
                .andExpect(jsonPath("$.healthAvaliation").value("Avaliação da saúde excelente"));
    }

    @Test
    public void testSearchNotFound() throws Exception {
        when(cityAvaliationRepository.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/avaliacao/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Avaliação não encontrada"));  // Verifica a mensagem de erro
    }

    @Test
    public void testCreateInvalidData() throws Exception {
        CityAvaliation invalidAvaliation = new CityAvaliation();
        invalidAvaliation.setCode(1);
        invalidAvaliation.setCityId(1);

        mockMvc.perform(post("/avaliacao")
                        .contentType("application/json")
                        .content("{\"cityId\": 1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreate() throws Exception {
        CityAvaliation avaliation = new CityAvaliation();
        avaliation.setCode(1);
        avaliation.setSchoolAvaliation("Avaliação das escolas ok");
        avaliation.setSecurityAvaliation("Avaliação da segurança ok");
        avaliation.setHealthAvaliation("Avaliação da saúde ok");
        avaliation.setCityId(1);

        when(cityAvaliationRepository.save(any(CityAvaliation.class))).thenReturn(avaliation);

        mockMvc.perform(post("/avaliacao")
                        .contentType("application/json")
                        .content("{\"schoolAvaliation\":\"Avaliação das escolas ok\",\"securityAvaliation\":\"Avaliação da segurança ok\",\"healthAvaliation\":\"Avaliação da saúde ok\",\"cityId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.schoolAvaliation").value("Avaliação das escolas ok"));
    }

    @Test
    public void testUpdate() throws Exception {
        CityAvaliation avaliation = new CityAvaliation();
        avaliation.setCode(1);
        avaliation.setSchoolAvaliation("Avaliação atualizada das escolas");
        avaliation.setSecurityAvaliation("Avaliação da segurança boa");
        avaliation.setHealthAvaliation("Avaliação da saúde boa");
        avaliation.setCityId(1);

        when(cityAvaliationRepository.save(any(CityAvaliation.class))).thenReturn(avaliation);
        when(cityAvaliationRepository.findById(1)).thenReturn(Optional.of(avaliation));

        mockMvc.perform(put("/avaliacao/1")
                        .contentType("application/json")
                        .content("{\"schoolAvaliation\":\"Avaliação atualizada das escolas\",\"securityAvaliation\":\"Avaliação da segurança boa\",\"healthAvaliation\":\"Avaliação da saúde boa\",\"cityId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.schoolAvaliation").value("Avaliação atualizada das escolas"));
    }

    @Test
    public void testDelete() throws Exception {
        when(cityAvaliationRepository.existsById(1)).thenReturn(true);
        doNothing().when(cityAvaliationRepository).deleteById(1);

        mockMvc.perform(delete("/avaliacao/1"))
                .andExpect(status().isNoContent());

        verify(cityAvaliationRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        when(cityAvaliationRepository.existsById(1)).thenReturn(false);

        mockMvc.perform(delete("/avaliacao/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Avaliação não encontrada"));
    }
}
