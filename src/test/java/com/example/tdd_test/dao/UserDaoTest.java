package com.example.tdd_test.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tdd_test.dto.req.RegistryReq;
import com.example.tdd_test.model.UserModel;
import com.example.tdd_test.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void findUserByAccount() {

        RegistryReq registryReq = new RegistryReq();
        registryReq.setAccount("account");
        registryReq.setEmail("email");
        registryReq.setName("name");
        registryReq.setPassword("password");
        userDao.createUser(registryReq);

        assertNotNull(userDao.findByAccount("account"));
        assertNull(userDao.findByAccount("account1"));

    }

}
