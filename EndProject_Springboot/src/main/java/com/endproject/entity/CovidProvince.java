package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author：乃王木木
 * @date 2022/12/16 13:33
 */
@Data
@TableName("covid_province")
public class CovidProvince {
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;
    //@TableField("total_cured")
    private String total_cured;
    //@TableField("total_death")
    private String total_death;
    //@TableField("child_statistic")
    private String child_statistic;
    //@TableField("total_confirmed")
    private String total_confirmed;
}
