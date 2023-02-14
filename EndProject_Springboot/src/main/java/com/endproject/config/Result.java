
package com.endproject.config;

import lombok.Data;


/**
 * @author：乃王木木
 * @date 2022/12/9 1:23
 */

@Data
public class Result<T> {
    private String code;
    private String msg;
    private Boolean isSuccess;
    private T data;
}

