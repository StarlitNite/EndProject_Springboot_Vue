package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/9 0:05
 */
@Data
@TableName("approval_process")
public class Leave {
    private Integer id;
    private String snum;
    private String reason;
    private String address;
    private Integer day;
    private String phone;
    private Integer node_status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;
}
