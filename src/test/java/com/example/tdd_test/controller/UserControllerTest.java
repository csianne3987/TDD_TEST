package com.example.tdd_test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.tdd_test.dto.rep.UserRep;
import com.example.tdd_test.dto.req.RegistryReq;
import com.example.tdd_test.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    private HttpHeaders httpHeaders;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeAll
    void init() {
        log.info("人員Controller測試");
    }

    @Test
    @DisplayName("新增人員")
    void registry() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        RegistryReq registryReq = new RegistryReq();
        registryReq.setAccount("aaa");
        registryReq.setEmail("email");
        registryReq.setName("name");
        registryReq.setPassword("password");

        UserRep userRep = new UserRep();
        userRep.setAccount(registryReq.getAccount());
        userRep.setEmail(registryReq.getEmail());
        userRep.setName(registryReq.getName());
        userRep.setId("id");

        when(userService.createUser(any(RegistryReq.class))).thenReturn(userRep);
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(
                post("/users/registry")
                        .headers(httpHeaders)
                        .content(objectMapper.writeValueAsString(registryReq)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("account").value(registryReq.getAccount()))
                .andExpect(jsonPath("email").value(registryReq.getEmail()))
                .andExpect(jsonPath("name").value(registryReq.getName()))
                .andExpect(jsonPath("id").exists());
    }

}
