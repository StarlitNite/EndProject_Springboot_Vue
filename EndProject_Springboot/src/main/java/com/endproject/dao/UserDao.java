package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.Model.dto.SupeditUserInfo;
import com.endproject.Model.dto.addUserInfo;
import com.endproject.entity.UserType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 乃王木木
 */
@Mapper
public interface UserDao extends BaseMapper<UserType> {

     Boolean addUserByInfo(addUserInfo addUserInfo);

     void editUserById(Integer userId, SupeditUserInfo supeditUserInfo);

     void stuupdateByid(Integer userId, String password);

    void saveAll(UserType userType);

    void ZeroHealthStatus();

    int deleteByPrimaryKey(Integer id);

    int insertchat(UserType user);

    int insertSelective(UserType user);

    UserType selectByPrimaryKey(String id);



    int updateByPrimaryKeySelective(UserType user);

    int updateByPrimaryKey(UserType user);

    String selectByName(String name);

    List<UserType> getCloudList(@Param("list") List<String> list);

    UserType selectUserByName(@Param("name") String name);
}
