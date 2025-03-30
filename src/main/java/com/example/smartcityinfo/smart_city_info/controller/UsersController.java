package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.model.Users;
import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/{code}")
    public Users getUser(@PathVariable int code) {
        return usersRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com código " + code + " não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Users createUser(@Valid @RequestBody Users user) {
        return usersRepository.save(user);
    }

    @PutMapping("/{code}")
    public Users updateUser(@Valid @RequestBody Users user, @PathVariable int code) {
        user.setCode(code);
        return usersRepository.save(user);
    }

    @DeleteMapping("/{code}")
    public void deleteUser(@PathVariable int code) {
        if (!usersRepository.existsById(code)) {
            throw new EntityNotFoundException("Usuário com código " + code + " não encontrado");
        }
        usersRepository.deleteById(code);
    }
}
