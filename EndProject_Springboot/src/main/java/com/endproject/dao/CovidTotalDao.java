package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.CovidTotal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author：乃王木木
 * @date 2022/12/16 2:12
 */
@Mapper
public interface CovidTotalDao extends BaseMapper<CovidTotal> {


    List<CovidTotal> getAllTotal();
}
