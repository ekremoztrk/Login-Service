package com.parksmartbackend.parksmart.payload.response;

import com.parksmartbackend.parksmart.model.User;

import java.util.List;

public class JwtResponse {

    private String token;

    private String type = "Bearer";

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long companyId;

    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {

        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String token, User user, List<String> roles) {

        this.token = token;
        this.id = user.getId();
        this.firstName = user.getFName();
        this.lastName = user.getLName();
        this.email = user.getEmail();
        this.roles = roles;
    }

    public String getAccessToken() {

        return token;
    }

    public void setAccessToken(String token) {

        this.token = token;
    }

    public String getTokenType() {

        return type;
    }

    public void setTokenType(String type) {

        this.type = type;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public Long getCompanyId() {

        return companyId;
    }

    public void setCompanyId(Long companyId) {

        this.companyId = companyId;
    }

    public void setRoles(List<String> roles) {

        this.roles = roles;
    }

    public List<String> getRoles() {

        return roles;
    }
}