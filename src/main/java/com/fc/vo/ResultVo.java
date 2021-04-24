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

    public static ResultVo fail(String message, Integer code) {
        ResultVo resultVo = new ResultVo();

        resultVo.setMessage(message);
        resultVo.setCode(code);
        resultVo.setSuccess(false);
        resultVo.setData(null);

        return resultVo;
    }
}
