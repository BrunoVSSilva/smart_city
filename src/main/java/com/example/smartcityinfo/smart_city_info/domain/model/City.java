package com.example.smartcityinfo.smart_city_info.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Código obrigatório!") // Adicionando validação para o código
    private int code;

    @NotBlank(message = "Nome obrigatório!") // Valida se o nome não está em branco
    @Size(max = 50, message = "O nome da cidade não pode ter mais de 50 caracteres.") // Valida o tamanho do nome
    private String name;

    @NotBlank(message = "Campo 'sobre' obrigatório!") // Valida se o campo 'about' não está em branco
    @Size(max = 255, message = "O campo 'sobre' não pode ter mais de 255 caracteres.") // Valida o tamanho do campo 'about'
    private String about;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "code")
    private Set<CityAvaliation> avaliations = new HashSet<>();

    // Getters e Setters
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public Set<CityAvaliation> getAvaliations() { return avaliations; }
    public void setAvaliations(Set<CityAvaliation> avaliations) { this.avaliations = avaliations; }
}
