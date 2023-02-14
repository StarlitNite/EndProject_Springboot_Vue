package com.endproject.Model.vo;

import com.endproject.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/14 21:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends UserType {
    private Integer Page;
    private Integer limit;
}