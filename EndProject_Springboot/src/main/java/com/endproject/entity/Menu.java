package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/9 17:51
 */
@Data
@TableName("menu")
public class Menu {
    private Integer id;
    private Integer pid;
    //private String type;
    private String title;
    //private String permission;
    private String path;
    //private Integer state;
    //private String menunum;
    private String icon;

    private List<Menu> child;
}
