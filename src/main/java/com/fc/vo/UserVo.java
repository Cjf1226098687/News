package com.fc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    private Integer id;
    private String name;
    private String phone;
    private Integer age;
    private Byte gender;
    private String avaUrl;
}
