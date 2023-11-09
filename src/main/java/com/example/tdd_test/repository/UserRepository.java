package com.example.tdd_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tdd_test.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String>, JpaSpecificationExecutor<UserModel> {

    @Query(value = "select u from UserModel u where u.account = :account")
    List<UserModel> findByAccount(@Param("account") String account);

}
