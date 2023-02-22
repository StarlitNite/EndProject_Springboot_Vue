package com.endproject.Model.vo;

import com.endproject.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/20 23:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajorVo extends Major {
    private Integer Page;
    private Integer limit;
}
