package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author：乃王木木
 * @date 2022/12/16 16:38
 */
@Data
@TableName("role")
public class Role {
    private Integer id;
    private String name;
    private String remark;
}
