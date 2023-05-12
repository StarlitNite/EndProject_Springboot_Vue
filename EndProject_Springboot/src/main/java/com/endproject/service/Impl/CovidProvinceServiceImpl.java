package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.CovidProvinceDao;
import com.endproject.entity.CovidProvince;
import com.endproject.service.CovidProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/20 16:42
 */
@Service
public class CovidProvinceServiceImpl extends ServiceImpl<CovidProvinceDao,CovidProvince> implements CovidProvinceService {

    @Autowired
    CovidProvinceDao covidProvinceDao;

    @Override
    public List<CovidProvince> getAllProvince() {
        return covidProvinceDao.getAllProvince();
    }
}
