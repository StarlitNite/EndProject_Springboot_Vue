package com.endproject.Model.vo;

import com.endproject.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/13 12:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo extends Role {
    private Integer Page;
    private Integer limit;
}
