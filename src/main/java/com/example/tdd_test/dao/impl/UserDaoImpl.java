package com.example.tdd_test.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.tdd_test.dao.UserDao;
import com.example.tdd_test.dto.req.RegistryReq;
import com.example.tdd_test.model.UserModel;
import com.example.tdd_test.repository.UserRepository;

import jakarta.validation.Valid;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel createUser(@Valid RegistryReq registryReq) {
        UserModel userModel = new UserModel();
        userModel.setAccount(registryReq.getAccount());
        userModel.setEmail(registryReq.getEmail());
        userModel.setName(registryReq.getName());
        userModel.setPassword(registryReq.getPassword());
        userRepository.save(userModel);
        return userModel;
    }

    @Override
    public UserModel findByAccount(String account) {
        List<UserModel> userList = userRepository.findByAccount(account);
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel editUser(RegistryReq registryReq) {
        UserModel userModel = userRepository.findById(registryReq.getId()).orElse(null);
        userModel.setAccount(registryReq.getAccount());
        userModel.setEmail(registryReq.getEmail());
        userModel.setName(registryReq.getName());
        userModel.setPassword(registryReq.getPassword());
        userRepository.save(userModel);
        return userModel;
    }

}
