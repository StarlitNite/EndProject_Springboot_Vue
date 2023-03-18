package com.endproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.Model.vo.LeaveVo;
import com.endproject.entity.Leave;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/9 16:20
 */
public interface LeaveService extends IService<Leave> {

    List<Leave> getLeaveByCou(String snum);

    List<Leave> getLeaveByStu(String snum);
}
