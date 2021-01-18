package com.mt.brightauthorization.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequestDTO {

    private long id;

    private String username;

    private String surname;

    private Integer age;

    private String phone;

    private String password;
}
