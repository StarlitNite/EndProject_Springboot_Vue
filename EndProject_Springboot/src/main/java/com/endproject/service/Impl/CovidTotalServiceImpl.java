package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.CovidTotalDao;
import com.endproject.entity.CovidTotal;
import com.endproject.service.CovidTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/16 2:12
 */
@Service
public class CovidTotalServiceImpl extends ServiceImpl<CovidTotalDao, CovidTotal> implements CovidTotalService {

    @Autowired
    CovidTotalDao covidTotalDao;

    @Override
    public List<CovidTotal> getAllTotal() {
        return covidTotalDao.getAllTotal();
    }
}
