package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author 乃王木木
 */
@Data
@TableName("user")
public class UserType {

    private Integer id;
    private String username;
    private String password;
    private String family_address;
    private String dorm_address;
    private String tel;
    private Integer age;
    private String gender;
    private String classe_id;
    private String counselor_id;
    private String salt;
    private Integer status;


    private Integer role_id;
    private String department_id;
    private String department_name;
    private String major_id;
    private String major_name;



}
