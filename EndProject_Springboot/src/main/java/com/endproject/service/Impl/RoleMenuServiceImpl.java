package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.Model.vo.RoleMenuOut;
import com.endproject.dao.RoleMenuDao;
import com.endproject.entity.RoleMenu;
import com.endproject.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/6 12:58
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {

    @Autowired
    RoleMenuDao roleMenuDao;

    @Override
    public List<RoleMenuOut> getRole(Integer rid){
        //1.根据前端传来的管理id获取相应权限
        List<RoleMenuOut> roleMenus = roleMenuDao.getRoleMenuOutByRid(rid);
        //2.组装树结构
        List<RoleMenuOut> roleMenuList = new ArrayList<>();
        //2.1 找出一级分类
        for (RoleMenuOut roleMenuOutDad:roleMenus){
            if (roleMenuOutDad.getPid() == 0){
                roleMenuList.add(roleMenuOutDad);
            }
        }

        for (RoleMenuOut roleMenuOutChild:roleMenuList){
            roleMenuOutChild.setRoleMenuOutChildList(getRoleMenuOutChildList(roleMenuOutChild,roleMenus));
        }


        return roleMenuList;
    }

    @Override
    public List<RoleMenuOut> getRoleMenuOutChildList(RoleMenuOut root,List<RoleMenuOut> all){
        List<RoleMenuOut> child = new ArrayList<>();
        for (RoleMenuOut roleMenuOutChild:all){
            if (roleMenuOutChild.getPid().equals(root.getId())){
                child.add(roleMenuOutChild);
            };

        }
        return child;
    }




    @Override
    public void deleteRoleByRid(Integer rid) {
        roleMenuDao.deleteRoleByRid(rid);
    }

    @Override
    public void SaveRoleMenu(Integer rid, Integer mid) {
        roleMenuDao.SaveRoleMenu(rid,mid);
    }

}
