package com.example.smartcityinfo.smart_city_info.domain.repository;

import com.example.smartcityinfo.smart_city_info.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findByName(String name);
}
