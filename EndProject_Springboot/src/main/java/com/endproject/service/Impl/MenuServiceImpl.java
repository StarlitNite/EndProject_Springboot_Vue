package com.endproject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.endproject.dao.MenuDao;
import com.endproject.entity.Menu;
import com.endproject.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/9 17:52
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao,Menu> implements MenuService {
    @Autowired
    MenuDao menuDao;

    @Override
    public List<Menu> MenuTree(Integer role_id) {
        //1.查询出Menu中的内容
        List<Menu> menus = menuDao.MenuTree(role_id);
        //2.组装成树结构
        List<Menu> menusList = new ArrayList<>();
        //找到一级分类
        for (Menu menuDad : menus){
            if (menuDad.getPid() == 0){
                menusList.add(menuDad);
            }
        }

        for (Menu menuChild:menusList){
            menuChild.setChild(getChild(menuChild,menus));
        }
        return menusList;
    }

    @Override
    public List<Menu> getChild(Menu root, List<Menu> all){
        List<Menu> child = new ArrayList<>();
        for (Menu menuChild:all){
            if (menuChild.getPid().equals(root.getId())){
                //找出下级分类
                menuChild.setChild(getChild(menuChild,all));
                child.add(menuChild);
            }
        }
        return child;
    }

   /* @Override
    public void AddMenu(Menu menu) {
        menuDao.AddMenu(menu);
    }*/
}
