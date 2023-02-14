package com.endproject.entity;

import lombok.Data;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/5 17:09
 */
@Data
public class RoleMenu {
    Integer id;
    Integer rid;
    Integer mid;

    List<RoleMenu> roleMenus;
}
