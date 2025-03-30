package com.example.smartcityinfo.smart_city_info.domain.repository;


import com.example.smartcityinfo.smart_city_info.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findByName(String name);
    List<City> findByAbout(String about);
    List<City> findByNameAndAbout(String name, String about);
}
