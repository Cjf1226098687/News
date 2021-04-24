package com.fc.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private String phone;
    private String password;
    private Integer age;
    private Byte gender;
    private String avaUrl;
    private String token;
    private String code;
}
