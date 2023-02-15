package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/15 16:19
 */
@Mapper
public interface DepartmentDao extends BaseMapper<Department> {
}
