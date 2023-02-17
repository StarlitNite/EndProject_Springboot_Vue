package com.endproject.Model.dto;

import lombok.Data;

/**
 * @author：乃王木木
 * @date 2022/12/9 19:13
 */
@Data
public class addUserInfo {
    private Integer snum;
    private String username;
    private String password;
    private String address;
    private Integer role_id;
    private Integer classe_id ;
    private Integer major_id;
    private Integer department_id;
    private Integer counselor_id;
}
