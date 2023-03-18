package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    private Date create_time;
    private Date update_time;
}
