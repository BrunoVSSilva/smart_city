package com.example.smartcityinfo.smart_city_info.domain.repository;

import com.example.smartcityinfo.smart_city_info.domain.model.CityAvaliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityAvaliationRepository extends JpaRepository<CityAvaliation, Integer> {
    List<CityAvaliation> findBySchoolAvaliation(String schoolAvaliation);
    List<CityAvaliation> findBySecurityAvaliation(String securityAvaliation);
    List<CityAvaliation> findByHealthAvaliation(String healthAvaliation);
}
