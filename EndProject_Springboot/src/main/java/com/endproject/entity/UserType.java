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
    private Integer snum;
    private String username;
    private String password;
    private String family_address;
    private String dorm_address;
    private String tel;
    private Integer age;
    private String gender;
    private String counselor_id;
    private String salt;
    private Integer status;


    private Integer role_id;
    private Integer classe_id;
    @TableField(exist = false)
    private String classe_name;
    private Integer department_id;
    @TableField(exist = false)
    private String department_name;
    private Integer major_id;
    @TableField(exist = false)
    private String major_name;



}
