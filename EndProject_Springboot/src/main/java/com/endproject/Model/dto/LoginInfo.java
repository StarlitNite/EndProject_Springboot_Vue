package com.endproject.Model.dto;

import lombok.Data;

/**
 * @author：乃王木木
 * @date 2022/12/9 18:13
 */
@Data
public class LoginInfo {
    private String username;
    private String password;
    private Integer role_id;

}
