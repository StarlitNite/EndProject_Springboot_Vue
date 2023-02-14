package com.endproject.controller;

import com.endproject.entity.CovidTotal;
import com.endproject.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author：乃王木木
 * @date 2022/12/9 11:04
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/query")
    public List<CovidTotal> queryData(){
        List<CovidTotal> list = indexService.list();

        return list;
    }
}
