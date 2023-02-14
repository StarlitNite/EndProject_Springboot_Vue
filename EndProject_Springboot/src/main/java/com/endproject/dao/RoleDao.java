package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/13 12:30
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {
}
