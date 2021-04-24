package com.fc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthVo extends UserVo {
    private String token;

    public UserAuthVo(UserVo userVo) {
        this.setId(userVo.getId());
        this.setName(userVo.getName());
        this.setAge(userVo.getAge());
        this.setAvaUrl(userVo.getAvaUrl());
        this.setGender(userVo.getGender());
        this.setPhone(userVo.getPhone());
    }
}
