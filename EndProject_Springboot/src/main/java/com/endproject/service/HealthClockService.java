package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.entity.HealthClock;

import java.util.Date;
import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/17 16:23
 */
public interface HealthClockService extends IService<HealthClock> {

    List<HealthClock> getHealthByStu(String snum,Integer Page,Integer Limit);

    boolean check(String snum, Date create_time);
}
