package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.CovidNewsDao;
import com.endproject.entity.CovidNews;
import com.endproject.service.CovidNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/5/12 17:46
 */
@Service
public class CovidNewsServiceImpl extends ServiceImpl<CovidNewsDao, CovidNews> implements CovidNewsService {

    @Autowired
    CovidNewsDao covidNewsDao;

    @Override
    public List<CovidNews> getIndexNewsList() {
        return covidNewsDao.getIndexNewsList();
    }

    @Override
    public List<CovidNews> getAllNewsList() {
        return covidNewsDao.getAllNewsList();
    }

}
