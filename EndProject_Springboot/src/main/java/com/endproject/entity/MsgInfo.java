package com.endproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/30 5:50
 */
@Data
public class MsgInfo implements Serializable {
    private Integer id;
    private String fromUserId;
    private String fromUserName;
    private String toUserId;
    private String toUserName;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private Integer unReadFlag;
}
