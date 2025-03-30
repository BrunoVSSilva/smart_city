package com.example.smartcityinfo.smart_city_info.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class CityAvaliation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @NotBlank(message = "Por favor, insira sua avaliação quanto às escolas.")
    @Size(max = 100)
    @Column(name = "school_avaliation")
    private String schoolAvaliation;

    @NotBlank(message = "Por favor, insira sua avaliação quanto à segurança.")
    @Size(max = 100)
    @Column(name = "security_avaliation")
    private String securityAvaliation;

    @NotBlank(message = "Por favor, insira sua avaliação quanto à saúde.")
    @Size(max = 100)
    @Column(name = "health_avaliation")
    private String healthAvaliation;

    @Column(name = "city_id")
    private int cityId;

    // Getters e Setters
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getSchoolAvaliation() { return schoolAvaliation; }
    public void setSchoolAvaliation(String schoolAvaliation) { this.schoolAvaliation = schoolAvaliation; }

    public String getSecurityAvaliation() { return securityAvaliation; }
    public void setSecurityAvaliation(String securityAvaliation) { this.securityAvaliation = securityAvaliation; }

    public String getHealthAvaliation() { return healthAvaliation; }
    public void setHealthAvaliation(String healthAvaliation) { this.healthAvaliation = healthAvaliation; }

    public int getCityId() { return cityId; }
    public void setCityId(int cityId) { this.cityId = cityId; }
}
