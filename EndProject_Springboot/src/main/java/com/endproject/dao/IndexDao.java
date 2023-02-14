package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.CovidTotal;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author：乃王木木
 * @date 2022/12/9 11:04
 */
@Mapper
public interface IndexDao extends BaseMapper<CovidTotal> {
}
