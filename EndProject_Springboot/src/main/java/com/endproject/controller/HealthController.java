package com.endproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.endproject.Model.vo.HealthVo;
import com.endproject.entity.UserType;
import com.endproject.entity.Health;
import com.endproject.service.HealthClockService;
import com.endproject.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangNaiLinn
 * @Description: 每日定时任务生成一个填报，等待填写，分为未填写/已填写状态
 * @date create in 2023/3/17 16:36
 */
@RestController
@RequestMapping("/health/")
@Slf4j
@Api(value = "每日填报接口",tags = {"每日填报接口"})
public class HealthController {
    @Autowired
    HealthClockService healthClockService;

    /**
    * @Description: 用自己的学号查询 当前表中的填报信息  单表查询
    * @date 2023/3/17 17:14
    * @author WangNaiLinn
    **/

   /* @ApiOperation("学生查询填报")
    @GetMapping("gethealthstu")
    public ApiResult<Object> getHealthByStu(String snum){
        //List<HealthClock> list = healthClockService.getHealthByStu(snum);
        return ApiResult.success("获取成功",list);
    }*/

    /**
    * @Description: 还是一样，用教职工号查询到导员id再去查询对应id的学生的填报
    * @date 2023/3/17 17:14
    * @author WangNaiLinn
    **/

    @ApiOperation("查询填报")
    @GetMapping("gethealth")
    public ApiResult<Object> getHealth(UserType userType, HealthVo healthVo){
        IPage<Health> page = new Page<>(healthVo.getPage(),healthVo.getLimit());
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(healthVo.getSnum()),("snum"),healthVo.getSnum());
        Integer role_id = userType.getRole_id();

        Map<String,Object> map = new HashMap<>();
        if (role_id==1){
           IPage<Health> iPage = healthClockService.page(page,queryWrapper);
           return ApiResult.success(iPage);
        }else if (role_id==2){
                map.put("Healths",healthClockService.getHealthByCou(userType.getSnum(),healthVo.getPage(),healthVo.getLimit()));
                map.put("totalHealth",healthClockService.count());
                map.put("totalPage",Math.ceil(healthClockService.count()/healthVo.getLimit()));
                map.put("pageSize",healthVo.getLimit());
                map.put("currentPage",healthVo.getPage());
                System.out.println(map);
                return ApiResult.success("获取学生填报信息成功",map);
        }else if (role_id==3){

            try {
                map.put("Healths",healthClockService.getHealthByStu(userType.getSnum(),healthVo.getPage(),healthVo.getLimit()));
                map.put("totalHealth",healthClockService.count());
                map.put("totalPage",Math.ceil(healthClockService.count()/healthVo.getLimit()));
                map.put("pageSize",healthVo.getLimit());
                map.put("currentPage",healthVo.getPage());
            }finally {
                return ApiResult.success("获取填报信息成功",map);
            }

        }
        return null;
    }

    @ApiOperation("创建填报")
    @PostMapping("addhealthclock")
    public ApiResult<Object> addhealthclock(@RequestBody Health health){
        System.out.println(health);
        if (healthClockService.check(health.getSnum(),health.getCreate_time())){
            return ApiResult.error("今日已填报，请勿重复填报");
        }else {
            healthClockService.save(health);
            return ApiResult.success("填报完成");
        }
        /*如果当天已存在，无法重复创建，但可修改  error:今日已提交 if（time == dd） return error
        * 判断当前snum最新的一条数据和当前添加数据的时间，如果是同一天则返回false  添加失败，如是修改则不需此思路\
        * 点击添加后，传来时间，用该snum对应的最新的一条数据的时间和其比对，若为同一天则报错
        * 判断当前snum对应的最新一条数据是否为今天
        * select * from health_clock where snum = #{snum} and
        * 如何传递今天的时间，前端传递？
        * SELECT count(*) FROM health_clock WHERE TO_DAYS(create_time) = TO_DAYS(NOW()) AND snum = #{}  */
    }

    @ApiOperation("修改填报")
    @PostMapping("updatehealthclock")
    public ApiResult<Object> updateHealthClockByStu(@RequestBody Health health){
        healthClockService.updateById(health);
        return ApiResult.success("修改成功");
    }

    @ApiOperation("删除填报")
    @PostMapping("deletehealthclock")
    public ApiResult<Object> deleteHealthClockByStu(Health health){
        healthClockService.removeById(health.getId());
        return ApiResult.success("删除成功");
    }
}
