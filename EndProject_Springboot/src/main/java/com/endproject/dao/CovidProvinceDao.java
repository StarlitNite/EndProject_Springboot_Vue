package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.CovidProvince;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/20 16:39
 */
@Mapper
public interface CovidProvinceDao extends BaseMapper<CovidProvince> {

    List<CovidProvince> getAllProvince();
}
