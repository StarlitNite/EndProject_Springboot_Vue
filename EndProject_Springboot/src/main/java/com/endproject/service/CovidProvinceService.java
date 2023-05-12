package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.entity.CovidProvince;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/20 16:42
 */
public interface CovidProvinceService extends IService<CovidProvince> {
    List<CovidProvince> getAllProvince();
}
