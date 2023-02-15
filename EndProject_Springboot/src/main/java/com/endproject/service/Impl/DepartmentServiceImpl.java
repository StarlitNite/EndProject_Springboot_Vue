package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.DepartmentDao;
import com.endproject.entity.Department;
import com.endproject.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/15 16:21
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department> implements DepartmentService {
}
