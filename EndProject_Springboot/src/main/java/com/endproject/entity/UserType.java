package com.endproject.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
/*import com.endproject.util.ExcelImport;*/
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 乃王木木
 */
@Data
@TableName("user")
@ExcelTarget("users")
public class UserType implements Serializable {

    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "学号")
    private String snum;
    @Excel(name = "用户名")
    private String username;
    @ExcelIgnore
    private String password;
    @Excel(name = "性别")
    private Integer gender;
    @Excel(name = "年级")
    private String grade;
    @Excel(name = "宿舍号")
    private String dorm_address;
    private String tel;
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
    private Integer counselor_id;
    @TableField(exist = false)
    private String counserlor_name;



}
