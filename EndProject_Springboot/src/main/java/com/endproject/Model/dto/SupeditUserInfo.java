package com.endproject.Model.dto;

import lombok.Data;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/17 13:36
 */
@Data
public class SupeditUserInfo {
    private String dorm_address;
    private String grade;
    private Integer role_id;
    private Integer classe_id;
    private Integer major_id;
    private Integer department_id;
    private Integer counselor_id;
}
