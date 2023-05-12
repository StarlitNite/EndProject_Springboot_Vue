package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.CovidTrendDao;
import com.endproject.entity.CovidTrend;
import com.endproject.service.CovidTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/15 19:41
 */
@Service
public class CovidTrendServiceImpl extends ServiceImpl<CovidTrendDao, CovidTrend> implements CovidTrendService {

    @Autowired
    CovidTrendDao covidTrendDao;

    @Override
    public List<CovidTrend> getAllTrend() {
        return covidTrendDao.getAllTrend();
    }
}
