package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.Model.dto.SupeditUserInfo;
import com.endproject.Model.dto.addUserInfo;
import com.endproject.entity.UserType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/1 19:37
 */
public interface UserService extends IService<UserType> {
    void addUserByInfo(addUserInfo addUserInfo);

    void editUserById(@Param("snum")  String snum, @Param("role_id") Integer role_id);

    void stuupdateByid(@Param("snum") String snum,@Param("password") String password);

    void saveAll(List<UserType> users);

    void ZeroHealthStatus();
}
