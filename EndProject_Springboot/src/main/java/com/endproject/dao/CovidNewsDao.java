package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.CovidNews;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/5/12 17:44
 */
@Mapper
public interface CovidNewsDao extends BaseMapper<CovidNews> {
    List<CovidNews> getIndexNewsList();


    List<CovidNews> getAllNewsList();
}
