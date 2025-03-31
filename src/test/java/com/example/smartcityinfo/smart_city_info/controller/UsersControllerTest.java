package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.domain.exception.EntityNotFoundException;
import com.example.smartcityinfo.smart_city_info.domain.model.Users;
import com.example.smartcityinfo.smart_city_info.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UsersController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllUsers() throws Exception {
        Users user1 = new Users();
        user1.setCode(1);
        user1.setName("Alice");
        user1.setRole("ADMIN");

        Users user2 = new Users();
        user2.setCode(2);
        user2.setName("Bob");
        user2.setRole("USER");

        List<Users> usersList = Arrays.asList(user1, user2);

        when(usersService.getAllUsers()).thenReturn(usersList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"));
    }

    @Test
    void shouldCreateUser() throws Exception {
        Users user = new Users();
        user.setName("Charlie");
        user.setRole("USER");

        Users savedUser = new Users();
        savedUser.setCode(1);
        savedUser.setName("Charlie");
        savedUser.setRole("USER");

        when(usersService.createUser(any(Users.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.name").value("Charlie"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        Users existingUser = new Users();
        existingUser.setCode(1);
        existingUser.setName("Alice");
        existingUser.setRole("ADMIN");

        Users updatedUser = new Users();
        updatedUser.setName("Alice Updated");
        updatedUser.setRole("USER");

        when(usersService.updateUser(any(Users.class), Mockito.eq(1))).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice Updated"))
                .andExpect(jsonPath("$.role").value("USER"));

        verify(usersService).updateUser(any(Users.class), Mockito.eq(1));
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistingUser() throws Exception {
        Users updatedUser = new Users();
        updatedUser.setCode(1);
        updatedUser.setName("Alice Updated");
        updatedUser.setRole("USER");

        when(usersService.updateUser(any(Users.class), Mockito.eq(1)))
                .thenThrow(new EntityNotFoundException("Usuário com código 1 não encontrado"));

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Usuário com código 1 não encontrado"));
    }


    @Test
    void shouldDeleteUser() throws Exception {
        Mockito.doNothing().when(usersService).deleteUser(1);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenDeletingNonExistingUser() throws Exception {
        Mockito.doThrow(new EntityNotFoundException("Usuário com código 1 não encontrado"))
                .when(usersService).deleteUser(1);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Usuário com código 1 não encontrado"));
    }
}
