package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/9 17:51
 */
@Mapper
public interface MenuDao extends BaseMapper<Menu> {
    List<Menu> MenuTree(Integer role_id);
    List<Menu> AllMenuTree();

    /*@Insert("INSERT INTO menu(pid,title,path) VALUES (#{pid},#{title},#{path})")
    void AddMenu(Menu menu);*/
}
