package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.CovidTrend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/15 19:41
 */
@Mapper
public interface CovidTrendDao extends BaseMapper<CovidTrend> {

    List<CovidTrend> getAllTrend();
}
