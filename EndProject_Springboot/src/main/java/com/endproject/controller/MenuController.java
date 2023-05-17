package com.endproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.endproject.entity.Menu;
import com.endproject.service.MenuService;
import com.endproject.util.ApiResult;
import com.endproject.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/9 17:51
 */
@RestController
@RequestMapping("/menu/")
@Slf4j
@Api(value = "菜单接口")
public class MenuController {

    @Autowired
    MenuService menuService;

    @ApiOperation("获取菜单")
    @GetMapping(value = "Menu")
    public ApiResult<Object> getMenu(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Object role_id = JwtUtil.parse(token);
        List<Menu> menu = menuService.MenuTree((Integer) role_id);
        return ApiResult.success("获取菜单成功",menu);
    }

    @ApiOperation("获取所有菜单")
    @GetMapping(value = "getAllMenu")
    public ApiResult<Object> getAllMenu(){
        List<Menu> menus = menuService.getAllMenu();
        return ApiResult.success(menus);
    }

    /**
    * @Description: 先获取菜单中等于0的菜单的id，等于其添加的Pid
    * @date 2023/2/13 10:39
    * @author WangNaiLinn
    **/
    @ApiOperation("新增菜单")
    @PostMapping("addMenu")
    public ApiResult<Object> addMenu(@RequestBody Menu menu){
        System.out.println(menu);
        menuService.save(menu);

        return ApiResult.success("新增菜单成功");
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("delMenu")
    public ApiResult<Object> delMenu(@PathVariable(value = "id") Integer id){
        System.out.println(id);
        menuService.removeById(id);
        return ApiResult.success();
    }

    @ApiOperation("修改菜单")
    @PostMapping("editMenu")//报错因为没加@RequestBody
    public ApiResult<Object> editMenu(@RequestBody Menu menu){
        System.out.println(menu);
        menuService.updateById(menu);
        return ApiResult.success("修改菜单成功");
    }
}
