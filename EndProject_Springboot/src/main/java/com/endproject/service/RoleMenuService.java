package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.Model.vo.RoleMenuOut;
import com.endproject.entity.Role;
import com.endproject.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/6 12:56
 */
public interface RoleMenuService extends IService<RoleMenu> {
    //权限树
    List<RoleMenuOut> getRole(Integer rid);
    List<RoleMenuOut> getRoleMenuOutChildList(RoleMenuOut root,List<RoleMenuOut> all);


    void deleteRoleByRid(Integer rid);

    void SaveRoleMenu(@Param("rid") Integer rid,@Param("mid") Integer mid);
}
