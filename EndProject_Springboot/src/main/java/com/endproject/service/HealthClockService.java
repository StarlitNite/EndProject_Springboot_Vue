package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.entity.Health;

import java.util.Date;
import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/17 16:23
 */
public interface HealthClockService extends IService<Health> {

    List<Health> getHealthByStu(String snum,Integer Page,Integer Limit);

    boolean check(String snum, Date create_time);

    List<Health> getHealthByCou(String snum, Integer page, Integer limit);
}
