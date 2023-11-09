package com.example.tdd_test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.tdd_test.dao.UserDao;
import com.example.tdd_test.dto.rep.UserRep;
import com.example.tdd_test.dto.req.RegistryReq;
import com.example.tdd_test.model.UserModel;
import com.example.tdd_test.service.UserService;

import jakarta.validation.Valid;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserRep createUser(@Valid RegistryReq registryReq) {
        UserModel userModel = userDao.findByAccount(registryReq.getAccount());
        if (userModel != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號已存在");
        }
        userModel = userDao.createUser(registryReq);
        UserRep userRep = new UserRep();
        userRep.setAccount(userModel.getAccount());
        userRep.setEmail(userModel.getEmail());
        userRep.setName(userModel.getName());
        userRep.setId(userModel.getId());
        return userRep;
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserModel editUser(RegistryReq registryReq) {
        return null;
    }

}
