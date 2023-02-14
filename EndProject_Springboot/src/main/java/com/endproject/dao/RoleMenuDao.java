package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.Model.vo.RoleMenuOut;
import com.endproject.entity.Role;
import com.endproject.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/5 17:08
 */
@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenu> {
    /**
     *获取角色权限
     * @return
     */
    List<RoleMenuOut> getRoleMenu();

    List<RoleMenuOut> getRoleMenuOutByRid(Integer rid);

    //分配菜单栏前删除rid对应的权限
    void deleteRoleByRid(Integer rid);
    //保存分配权限
    void SaveRoleMenu(@Param("rid")Integer rid,@Param("mid") Integer mid);




}
