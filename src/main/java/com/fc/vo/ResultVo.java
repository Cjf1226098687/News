package com.fc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {
    private String message;
    private Integer code;
    private boolean success;
    private Object data;

    public ResultVo(Object data) {
        this.message = "OK";
        this.code = 200;
        this.success = true;
        this.data = data;
    }
}
