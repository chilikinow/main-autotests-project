package com.company.project.demowebshop.model;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;

}
