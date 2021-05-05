package com.parksmartbackend.parksmart.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "username.blank")
    @Getter
    private String email;

    @NotBlank(message = "password.blank")
    @Getter
    private String password;

}
