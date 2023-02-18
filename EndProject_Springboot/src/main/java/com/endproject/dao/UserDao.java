package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.Model.dto.SupeditUserInfo;
import com.endproject.Model.dto.addUserInfo;
import com.endproject.entity.UserType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 乃王木木
 */
@Mapper
public interface UserDao extends BaseMapper<UserType> {

     Boolean addUserByInfo(addUserInfo addUserInfo);

     void editUserById(Integer userId, SupeditUserInfo supeditUserInfo);

     void stuupdateByid(Integer userId, String password);
}
