package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.RoleDao;
import com.endproject.entity.Role;
import com.endproject.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/13 12:30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService  {
}
