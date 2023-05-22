package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.entity.CovidNews;
import com.endproject.entity.CovidProvince;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/5/12 17:45
 */
public interface CovidNewsService extends IService<CovidNews> {
    List<CovidNews> getIndexNewsList();


    List<CovidNews> getAllNewsList();
}
