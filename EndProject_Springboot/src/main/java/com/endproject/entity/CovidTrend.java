package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author：乃王木木
 * @date 2022/12/15 16:30
 */
@TableName("covid_trend")
@Data
public class CovidTrend {
    @TableId(value = "id" ,type = IdType.INPUT)
    private Integer id ;
    private String day;
    private String sure_cnt;
    private String die_cnt;
    private String cure_cnt;
    private String doubt_cnt;
    private String full_day;
}
