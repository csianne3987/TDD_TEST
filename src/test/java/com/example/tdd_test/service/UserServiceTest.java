package com.example.tdd_test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.example.tdd_test.dao.UserDao;
import com.example.tdd_test.dto.rep.UserRep;
import com.example.tdd_test.dto.req.RegistryReq;
import com.example.tdd_test.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

    private RegistryReq registryReq;
    private UserModel userModel;
    private UserRep userRep;

    @BeforeAll
    void init() {
        RegistryReq registryReq = new RegistryReq();
        registryReq.setAccount("account");
        registryReq.setEmail("email");
        registryReq.setName("name");
        registryReq.setPassword("password");

        UserModel userModel = new UserModel();
        userModel.setAccount(registryReq.getAccount());
        userModel.setEmail(registryReq.getEmail());
        userModel.setId("id");
        userModel.setName(registryReq.getName());
        userModel.setPassword(registryReq.getPassword());

        UserRep userRep = new UserRep();
        userRep.setAccount(userModel.getAccount());
        userRep.setEmail(userModel.getEmail());
        userRep.setId(userModel.getId());
        userRep.setName(userModel.getName());
    }

    @Test
    @DisplayName("新增人員")
    void createUser() {

        when(userDao.createUser(registryReq)).thenReturn(userModel);
        assertEquals(userRep.getAccount(), userService.createUser(registryReq).getAccount());
        assertEquals(userRep.getEmail(), userService.createUser(registryReq).getEmail());
        assertEquals(userRep.getId(), userService.createUser(registryReq).getId());
        assertEquals(userRep.getName(), userService.createUser(registryReq).getName());

    }

    @Test
    @DisplayName("新增重複人員")
    void createDuplicateUser() {
        when(userDao.findByAccount(registryReq.getAccount())).thenReturn(userModel);
        when(userDao.createUser(registryReq)).thenReturn(userModel);
        assertThrows(ResponseStatusException.class, () -> {
            userService.createUser(registryReq);
        });
    }

    @Test
    @DisplayName("編輯人員")
    void editUser() {

        when(userDao.editUser(registryReq)).thenReturn(userModel);
        UserModel userModel = userService.editUser(registryReq);

    }

    @Test
    @DisplayName("刪除人員")
    void deleteUser() {

        userService.deleteUser("id");

        verify(userDao).deleteUser("id");

    }

}
