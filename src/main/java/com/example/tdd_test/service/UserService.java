package com.example.tdd_test.service;

import com.example.tdd_test.dto.rep.UserRep;
import com.example.tdd_test.dto.req.RegistryReq;
import com.example.tdd_test.model.UserModel;

import jakarta.validation.Valid;

public interface UserService {

    UserRep createUser(@Valid RegistryReq registryReq);

    void deleteUser(String id);

    UserModel editUser(RegistryReq registryReq);

}
