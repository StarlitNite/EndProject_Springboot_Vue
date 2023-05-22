package com.endproject.controller;

import com.endproject.Model.dto.LoginInfo;
import com.endproject.entity.CovidNews;
import com.endproject.service.CovidNewsService;
import com.endproject.service.CovidProvinceService;
import com.endproject.service.CovidTotalService;
import com.endproject.service.CovidTrendService;
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
 * @Description:
 * @date create in 2023/5/9 1:46
 */
@RestController
@RequestMapping("/covid/")
@Slf4j
@Api(value = "疫情接口" ,tags = {"疫情接口"})
public class CovidController {

    @Autowired
    CovidNewsService covidNewsService;
    @Autowired
    CovidTotalService covidTotalService;
    @Autowired
    CovidProvinceService covidProvinceService;
    @Autowired
    CovidTrendService covidTrendService;

    @ApiOperation(value = "疫情数据")
    @GetMapping(value = "covidData")
    public ApiResult<Object> total(){
        Map<String,Object> map = new HashMap<>();
        System.out.println(covidProvinceService.getAllProvince());
        map.put("Bar",covidProvinceService.getAllProvince());
        map.put("Map",covidProvinceService.getAllProvince());
        map.put("Statistic",covidTotalService.getAllTotal());
        map.put("Line",covidTrendService.getAllTrend());
        return ApiResult.success(map);
    }


    /**
    * @Description:  列表状显示在index上，不可点击、仅做展示？
    * @date 2023/5/12 21:19
    * @author WangNaiLinn
    **/

    @ApiOperation(value = "首页疫情新闻")
    @GetMapping(value = "covidNews")
    public ApiResult<Object> covidNews(){
        List<CovidNews> newsList = covidNewsService.getIndexNewsList();
        return ApiResult.success(newsList);
    }

    @ApiOperation(value = "所有疫情新闻")
    @GetMapping(value = "getAllNews")
    public ApiResult<Object> getAllNews(){
        List<CovidNews> newsList = covidNewsService.getAllNewsList();
        return ApiResult.success(newsList);
    }

    @ApiOperation(value = "添加疫情新闻")
    @PostMapping(value = "addcovidNews")
    public ApiResult<Object> addcovidNews(@RequestBody CovidNews covidNews){
        covidNewsService.save(covidNews);
        return ApiResult.success();
    }

    @ApiOperation(value = "修改疫情新闻")
    @PostMapping(value = "editcovidNews")
    public ApiResult<Object> editcovidNews(@RequestBody CovidNews covidNews){
        covidNewsService.updateById(covidNews);
        return ApiResult.success("修改成功");
    }

    @ApiOperation(value = "删除疫情新闻")
    @PostMapping(value = "delcovidNews/{id}")
    public ApiResult<Object> delcovidNews(@PathVariable Integer id){
        covidNewsService.removeById(id);
        return ApiResult.success();
    }


}
