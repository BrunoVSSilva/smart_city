package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.model.Users;
import com.example.smartcityinfo.smart_city_info.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
        Users savedUser = usersService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Users> updateUser(@Valid @RequestBody Users user, @PathVariable int code) {
        Users updatedUser = usersService.updateUser(user, code);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteUser(@PathVariable int code) {
        usersService.deleteUser(code);
        return ResponseEntity.noContent().build();
    }
}