package com.endproject.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/30 5:48
 */
@Data
public class SessionList implements Serializable {

    private Integer id;
    private String userId;
    private String toUserId;
    private String listName;
    private Integer unReadCount;
}
