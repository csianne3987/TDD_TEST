package com.example.tdd_test.dao;

import com.example.tdd_test.dto.req.RegistryReq;
import com.example.tdd_test.model.UserModel;

import jakarta.validation.Valid;

public interface UserDao {

    UserModel createUser(@Valid RegistryReq registryReq);

    UserModel findByAccount(String account);

    void deleteUser(String id);

    UserModel editUser(RegistryReq registryReq);

}
