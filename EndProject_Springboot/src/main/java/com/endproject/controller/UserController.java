package com.endproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.endproject.Model.dto.LoginInfo;
import com.endproject.Model.vo.UserVo;
import com.endproject.entity.UserType;
import com.endproject.service.UserService;
import com.endproject.util.JwtUtil;
import com.endproject.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 乃王木木
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "登录接口")
public class UserController {

    @Autowired
    UserService userService;
    /**
     * 前后端联调
     */

    /**
    * @Description:登录
    * @date 2022/12/23 12:15
    * @author WangNaiLinn
    **/

    @ApiOperation(value = "登录")
    @GetMapping(value = "/login")
    public ApiResult<Object> login(@RequestBody LoginInfo loginInfo){
        log.info("前端消息发过来了:{}", loginInfo);
        //QueryWrapper<UserType> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UserType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserType::getUsername,loginInfo.getUsername())
                    .and(m->m.eq(UserType::getPassword,loginInfo.getPassword()))
                    .and(ms->ms.eq(UserType::getRole_id,loginInfo.getRole_id()));

        UserType userType = userService.getOne(queryWrapper);

        if (userType == null){
            return ApiResult.error401("登录失败,请检查账号及密码");
        }
        Map<String,Object> map = new HashMap<>();
        //Jwt身份权限
        String Jwt = JwtUtil.CreateJwt(loginInfo);
        map.put("user",userType.getUsername());
        map.put("role",userType.getRole_id());
        map.put("token",Jwt);
        System.out.println(map);
        return ApiResult.success("登录成功",map);
    }

    @ApiOperation("获取用户")
    @PostMapping("/get")
    public ApiResult<Object> getUser(UserVo userVo){
        Page<>

        return null;
    }


}
