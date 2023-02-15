package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author：乃王木木
 * @date 2022/12/16 16:41
 */
@TableName("department")
@Data
public class Department {
    private Integer id;
    private String department_name;
}
