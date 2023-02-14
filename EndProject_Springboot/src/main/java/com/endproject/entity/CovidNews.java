package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * @author：乃王木木
 * @date 2022/12/16 17:53
 */
@Data
@TableName("covid_news")
public class CovidNews {
    private Integer id;
    private String title;
    private String content;
    private Date create_time;
    private String publishby;
}
