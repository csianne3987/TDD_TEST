package com.example.tdd_test.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistryReq {

    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String account;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
