package com.endproject.controller;

import com.endproject.Model.dto.LoginInfo;
import com.endproject.service.CovidProvinceService;
import com.endproject.service.CovidTotalService;
import com.endproject.service.CovidTrendService;
import com.endproject.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/5/9 1:46
 */
@RestController
@RequestMapping("/covid/")
@Slf4j
@Api(value = "疫情接口")
public class CovidController {
    @Autowired
    CovidTotalService covidTotalService;
    @Autowired
    CovidProvinceService covidProvinceService;
    @Autowired
    CovidTrendService covidTrendService;

    @ApiOperation(value = "")
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
    * @Description:  列表状显示在index上，不可点击、仅做展示？退出功能 消息通知  通知  WebSocket
    * @date 2023/5/12 21:19
    * @author WangNaiLinn
    **/


}
