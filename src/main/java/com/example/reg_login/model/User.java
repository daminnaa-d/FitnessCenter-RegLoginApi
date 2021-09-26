package com.example.reg_login.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private int money;
    private String phoneNumber;
    private String birthday ;
    private String pswd;

}
