package com.endproject.Model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/6 16:09
 */
@Data
public class RoleMenuOut {
    private Integer id;
    private Integer Pid;
    private String title;

    List<RoleMenuOut> roleMenuOutChildList;
}
