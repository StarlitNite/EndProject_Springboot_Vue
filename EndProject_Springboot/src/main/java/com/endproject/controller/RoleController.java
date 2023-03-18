package com.endproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.endproject.Model.vo.RoleMenuOut;
import com.endproject.Model.vo.RoleVo;
import com.endproject.dao.RoleDao;
import com.endproject.entity.Role;
import com.endproject.service.RoleMenuService;
import com.endproject.service.RoleService;
import com.endproject.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/5 17:07
 */
@RestController
@RequestMapping("/role/")
@Slf4j
@Api("权限接口")
public class RoleController {
    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleDao roleDao;

    /**
    * @Description: 权限管理  分配权限前置接口
    * @date 2023/2/6 17:59
    * @author WangNaiLinn
    **/
    @ApiOperation("获取权限")
    @GetMapping("Role/{rid}")
    public ApiResult<Object> getRole(@PathVariable(value = "rid") Integer rid){
        List<RoleMenuOut> Role = roleMenuService.getRole(rid);
        return ApiResult.success(Role);
    }

    /**
    * @Description: 权限管理 分配权限后置接口
    * @date 2023/2/11 21:23
    * @author WangNaiLinn
    **/
    
    @ApiOperation("权限变动")
    @PostMapping("SaveRole")
    @ResponseBody
    public ApiResult<Object> SaveRole(@Param("rid") Integer rid,@Param("mids") Integer[] mids){
        //1.删除rid对应权限

        roleMenuService.deleteRoleByRid(rid);
        //2.循环添加rid对应权限
        if (mids != null && mids.length>0){
            for (Integer mid:mids){
                roleMenuService.SaveRoleMenu(rid,mid);
                System.out.println(mid);
            }
        }
        /*System.out.println("rid为"+rid);
        System.out.println("mid为"+mids.toString());*/

        return ApiResult.success(mids);

    }
    /**
    * @Description:查询角色，带分页带查询条件
    * @date 2023/2/13 12:46
    * @author WangNaiLinn
    **/

    @ApiOperation("查询角色")
    @GetMapping("getRole")
    public ApiResult<Object> getRole(RoleVo roleVo){
        Page<Role> page = new Page<>(roleVo.getPage(),roleVo.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getName()),"name",roleVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getRemark()),"remark",roleVo.getRemark());
        /*List<Role> list = roleService.list();
        System.out.println(list);*/
        IPage<Role> page1 = roleService.page(page,queryWrapper);


        return ApiResult.success("获取角色成功",page1);
    }

    @ApiOperation("添加角色")
    @PostMapping("addRole")
    public ApiResult<Object> addRole(Role role){
        roleService.save(role);
        return ApiResult.success("添加角色成功");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("DeletRole")
    public ApiResult<Object> DeleteRole(Role role){

        roleService.removeById(role.getId());
        return ApiResult.success("删除角色成功");
    }

    @ApiOperation("修改角色")
    @PostMapping("UpdateRole")
    public ApiResult<Object> UpdateRole(Role role){
        System.out.println(role);
        roleService.saveOrUpdate(role);
        return ApiResult.success("修改角色成功");
    }
}
