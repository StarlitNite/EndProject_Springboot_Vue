package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.HealthClock;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author WangNaiLinn
 * @Description: 每日填报
 * @date create in 2023/3/17 16:23
 */
@Mapper
public interface HealthClockDao extends BaseMapper<HealthClock> {

    List<HealthClock> getHealthByStu(String snum,Integer Page,Integer Limit);

    boolean check(String snum, Date create_time);
}
