package com.example.smartcityinfo.smart_city_info.service;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.model.Users;
import com.example.smartcityinfo.smart_city_info.domain.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    private Users user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        user = new Users();
        user.setCode(1);
        user.setName("User 1");
    }

    @Test
    public void shouldGetUserByCode() {
        when(usersRepository.findById(1)).thenReturn(Optional.of(user));

        Users foundUser = usersService.getUserByCode(1);
        assertEquals(1, foundUser.getCode());
        assertEquals("User 1", foundUser.getName());
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        when(usersRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> usersService.getUserByCode(1));
    }

    @Test
    public void shouldCreateUser() {
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        Users createdUser = usersService.createUser(user);
        assertNotNull(createdUser);
        assertEquals(1, createdUser.getCode());
    }

    @Test
    public void shouldUpdateUser() {
        when(usersRepository.existsById(1)).thenReturn(true);
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        Users updatedUser = usersService.updateUser(user, 1);
        assertNotNull(updatedUser);
        assertEquals(1, updatedUser.getCode());
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNonExistentUser() {
        when(usersRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> usersService.updateUser(user, 1));
    }

    @Test
    public void shouldDeleteUser() {
        when(usersRepository.existsById(1)).thenReturn(true);
        doNothing().when(usersRepository).deleteById(1);

        usersService.deleteUser(1);

        verify(usersRepository, times(1)).deleteById(1);
    }

    @Test
    public void shouldThrowExceptionWhenDeletingNonExistentUser() {
        when(usersRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> usersService.deleteUser(1));
    }
}
