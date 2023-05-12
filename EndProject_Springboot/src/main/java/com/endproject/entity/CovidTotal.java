package com.endproject.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * @author：乃王木木
 * @date 2022/12/9 11:05
 */
@Data
@TableName("covid_total")
public class CovidTotal {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    //@TableField(value = "total_cured")
    private String total_cured; //全国累计治愈数
    //@TableField(value = "total_death")
    private String total_death; //全国累计死亡数
    //@TableField(value = "inc_doubtful")
    private String inc_doubtful; //全国疑似数，较昨日对比
    //@TableField(value = "child_statistic")
    private String child_statistic; //“全国”
    //@TableField(value = "total_doubtful")
    private String total_doubtful; //全国疑似数
    //@TableField(value = "total_confirmed")
    private String total_confirmed; //全国累计确诊数
    //@TableField(value = "update_time")
    private String update_time; //更新时间
    //@TableField(value = "data_source")
    private String data_source; //数据来源


}



