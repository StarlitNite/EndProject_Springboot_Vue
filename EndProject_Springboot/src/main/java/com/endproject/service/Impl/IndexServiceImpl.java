package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.IndexDao;
import com.endproject.entity.CovidTotal;
import com.endproject.service.IndexService;
import org.springframework.stereotype.Service;

/**
 * @author：乃王木木
 * @date 2022/12/9 11:09
 */
@Service
public class IndexServiceImpl extends ServiceImpl<IndexDao, CovidTotal> implements IndexService {
}
