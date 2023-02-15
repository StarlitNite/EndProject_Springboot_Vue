package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.MajorDao;
import com.endproject.entity.Major;
import com.endproject.service.MajorService;
import org.springframework.stereotype.Service;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/15 16:43
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorDao, Major> implements MajorService {
}
