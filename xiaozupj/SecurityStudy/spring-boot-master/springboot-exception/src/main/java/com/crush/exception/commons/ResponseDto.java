package com.crush.exception.commons;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author crush
 */
@Data
@NoArgsConstructor
public class ResponseDto<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体的内容
     */
    private T data;


    public ResponseDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
    public static ResponseDto success(Object object){
        ResponseDto result = new ResponseDto();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(object);
        return result;
    }
}