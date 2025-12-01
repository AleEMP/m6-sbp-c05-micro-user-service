package com.tecsup.app.micro.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
class UserControllerTest {

    // Object Mapper
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsers() throws Exception {
        int NRO_RECORD = 6;
        final int ID_FIRST_RECORD = 1;

        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(NRO_RECORD)))
                .andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
    }

    @Test
    void getUserById() throws Exception {
        String NAME = "Juan Pérez";
        String EMAIL = "juan.perez@example.com";

        this.mockMvc.perform(get("/api/users/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.email", is(EMAIL)));
    }

    @Test
    @Transactional
    void createUser() throws Exception {
        String NAME = "Nuevo Usuario";
        String EMAIL = "nuevo.usuario@example.com";

        Map<String, Object> body = new HashMap<>();
        body.put("name", NAME);
        body.put("email", EMAIL);
        String jsonBody = om.writeValueAsString(body);
        this.mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.email", is(EMAIL)));
    }

    @Test
    @Transactional
    void updateUser() throws Exception {
        final int ID = 1;
        String UPDATED_NAME = "Juan Actualizado";
        String UPDATED_EMAIL = "juan.update@example.com";

        Map<String, Object> body = new HashMap<>();
        body.put("name", UPDATED_NAME);
        body.put("email", UPDATED_EMAIL);

        this.mockMvc.perform(put("/api/users/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.name", is(UPDATED_NAME)))
                .andExpect(jsonPath("$.email", is(UPDATED_EMAIL)));
    }

    @Test
    @Transactional
    void deleteUser() throws Exception {
        final int ID = 1;

        this.mockMvc.perform(delete("/api/users/" + ID))
                .andExpect(status().isNoContent());
    }
}