package com.endproject.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
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
    private String totalCured; //全国累计治愈数
    private String totalDeath; //全国累计死亡数
    private String incDoubtful; //全国疑似数，较昨日对比
    private String childStatistic; //“全国”
    private String totalDoubtful; //全国疑似数
    private String totalConfirmed; //全国累计确诊数
    private String updateTime; //更新时间
    private String dataSource; //数据来源


}



