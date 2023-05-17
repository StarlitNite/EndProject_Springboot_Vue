package com.endproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.endproject.entity.Menu;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/9 17:52
 */
public interface MenuService extends IService<Menu> {
    //菜单树
    List<Menu> MenuTree(Integer role_id);

    List<Menu> MenuTree();

    List<Menu> getChild(Menu root,List<Menu> all);

    List<Menu> getAllMenu();



    /*void AddMenu(Menu menu);*/

    //新增菜单

}
