package com.example.smartcityinfo.smart_city_info.service;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.model.Users;
import com.example.smartcityinfo.smart_city_info.domain.repository.UsersRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserByCode(int code) {
        return usersRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com código " + code + " não encontrado"));
    }

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    public Users updateUser(Users user, int code) {
        if (!usersRepository.existsById(code)) {
            throw new EntityNotFoundException("Usuário com código " + code + " não encontrado");
        }
        user.setCode(code);
        return usersRepository.save(user);
    }

    public void deleteUser(int code) {
        if (!usersRepository.existsById(code)) {
            throw new EntityNotFoundException("Usuário com código " + code + " não encontrado");
        }
        usersRepository.deleteById(code);
    }
}
