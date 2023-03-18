package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.HealthClockDao;
import com.endproject.entity.HealthClock;
import com.endproject.service.HealthClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/17 16:24
 */
@Service
public class HealthClockServiceImpl extends ServiceImpl<HealthClockDao, HealthClock> implements HealthClockService {

    @Autowired
    HealthClockDao healthClockDao;

    @Override
    public List<HealthClock> getHealthByStu(String snum,Integer Page,Integer Limit) {
        return healthClockDao.getHealthByStu(snum,Page,Limit);
    }

    @Override
    public boolean check(String snum, Date create_time) {
        return healthClockDao.check(snum,create_time);
    }


}
