package com.endproject.Model.vo;

import com.endproject.entity.Health;
import lombok.Data;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/18 17:55
 */
@Data
public class HealthVo extends Health {
    private Integer Page;
    private Integer Limit;
}
