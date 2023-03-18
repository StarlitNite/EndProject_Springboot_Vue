package com.endproject.Model.vo;

import com.endproject.entity.HealthClock;
import lombok.Data;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/18 17:55
 */
@Data
public class HealthVo extends HealthClock {
    private Integer Page;
    private Integer Limit;
}
