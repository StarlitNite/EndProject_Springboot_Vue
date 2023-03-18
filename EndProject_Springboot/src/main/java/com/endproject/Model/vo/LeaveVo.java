package com.endproject.Model.vo;

import com.endproject.entity.Leave;
import lombok.Data;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/9 16:18
 */
@Data
public class LeaveVo extends Leave {
    private Integer Page;
    private Integer limit;
}
