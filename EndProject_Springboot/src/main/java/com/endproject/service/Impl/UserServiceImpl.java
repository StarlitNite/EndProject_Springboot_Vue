package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.Model.dto.SupeditUserInfo;
import com.endproject.Model.dto.addUserInfo;
import com.endproject.controller.MenuController;
import com.endproject.dao.UserDao;
import com.endproject.entity.UserType;
import com.endproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/1 19:37
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,UserType> implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public void addUserByInfo(addUserInfo addUserInfo) {
         userDao.addUserByInfo(addUserInfo);
    }

    @Override
    public void editUserById(Integer userId, SupeditUserInfo supeditUserInfo) {
        userDao.editUserById(userId, supeditUserInfo);
    }

    @Override
    public void stuupdateByid(Integer userId, String password) {
        userDao.stuupdateByid(userId,password);
    }

    @Override
    public void saveAll(List<UserType> users) {
        for (UserType userType:users){
            userType.setId(null);
            userDao.saveAll(userType);
        }
    }

    @Override
    public void ZeroHealthStatus() {
        userDao.ZeroHealthStatus();
    }
}
