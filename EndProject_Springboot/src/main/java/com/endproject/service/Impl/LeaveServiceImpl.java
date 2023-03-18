package com.endproject.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.Model.vo.LeaveVo;
import com.endproject.dao.LeaveDao;
import com.endproject.entity.Leave;
import com.endproject.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/9 16:20
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveDao, Leave> implements LeaveService {
    @Autowired
    LeaveDao leaveDao;


    @Override
    public List<Leave> getLeaveByCou(String snum) {
        return leaveDao.getLeaveByCou(snum);
    }

    @Override
    public List<Leave> getLeaveByStu(String snum) {
        return leaveDao.getLeaveByStu(snum);
    }
}
