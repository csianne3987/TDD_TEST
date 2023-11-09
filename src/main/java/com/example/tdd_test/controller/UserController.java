package com.example.tdd_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.tdd_test.dto.rep.UserRep;
import com.example.tdd_test.dto.req.RegistryReq;
import com.example.tdd_test.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("users/registry")
    public UserRep registry(@RequestBody @Valid RegistryReq registryReq) {
        UserRep userRep = userService.createUser(registryReq);
        return userRep;
    }

}
