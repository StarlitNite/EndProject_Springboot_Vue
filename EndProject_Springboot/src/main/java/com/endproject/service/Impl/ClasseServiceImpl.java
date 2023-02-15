package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.ClasseDao;
import com.endproject.entity.Classe;
import com.endproject.service.ClasseService;
import org.springframework.stereotype.Service;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/15 16:18
 */
@Service
public class ClasseServiceImpl extends ServiceImpl<ClasseDao, Classe> implements ClasseService {
}
