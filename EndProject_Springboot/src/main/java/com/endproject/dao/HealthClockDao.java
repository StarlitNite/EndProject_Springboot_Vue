package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.Health;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author WangNaiLinn
 * @Description: 每日填报
 * @date create in 2023/3/17 16:23
 */
@Mapper
public interface HealthClockDao extends BaseMapper<Health> {

    List<Health> getHealthByStu(@Param("snum") String snum, @Param("page") Integer page, @Param("limit") Integer limit);

    int check(@Param("snum")String snum,@Param("create_time") Date create_time);

    List<Health> getHealthByCou(@Param("snum") String snum,@Param("page") Integer page,@Param("limit") Integer limit);
}
