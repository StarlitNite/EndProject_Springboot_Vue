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

    @Excel(name = "编号",orderNum = "0")
    private Integer id;
    @Excel(name = "学号",orderNum = "1")
    private String snum;
    @Excel(name = "用户名",orderNum = "2")
    private String username;
    @ExcelIgnore
    private String password;
    @Excel(name = "性别",orderNum = "3")
    private Integer gender;
    @Excel(name = "年级",orderNum = "4")
    private String grade;
    @Excel(name = "宿舍号",orderNum = "5")
    private String dorm_address;
    private String tel;
    private String salt;
    private Integer status;
    private Integer health_status;
    private Integer nstu;



    private Integer role_id;

    @TableField(exist = false)
    private String role_name;

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
    private String counselor_name;



}
