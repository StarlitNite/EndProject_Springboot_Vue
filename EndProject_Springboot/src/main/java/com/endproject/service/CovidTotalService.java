package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.entity.CovidTotal;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/16 2:13
 */
public interface CovidTotalService extends IService<CovidTotal> {
    List<CovidTotal> getAllTotal();
}
