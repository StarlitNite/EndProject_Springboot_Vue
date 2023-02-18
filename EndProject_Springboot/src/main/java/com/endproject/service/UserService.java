package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.Model.dto.SupeditUserInfo;
import com.endproject.Model.dto.addUserInfo;
import com.endproject.entity.UserType;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/1 19:37
 */
public interface UserService extends IService<UserType> {
    void addUserByInfo(addUserInfo addUserInfo);

    void editUserById(Integer userId, SupeditUserInfo supeditUserInfo);

    void stuupdateByid(Integer userId, String password);
}
