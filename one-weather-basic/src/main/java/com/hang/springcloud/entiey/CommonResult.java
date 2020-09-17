package com.hang.springcloud.entiey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hang
 * @date 2020-07-19 16:06
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult<T> {  //json 封装主题类

    private Integer code;
    private String  message;
    private T  data;
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}