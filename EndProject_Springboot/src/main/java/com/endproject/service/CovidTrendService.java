package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.entity.CovidTrend;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/15 19:41
 */
public interface CovidTrendService extends IService<CovidTrend> {
    List<CovidTrend> getAllTrend();
}
