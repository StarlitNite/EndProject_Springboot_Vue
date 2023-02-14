package com.endproject.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.endproject.entity.CovidProvince;
import com.endproject.entity.CovidTotal;
import com.endproject.entity.CovidTrend;
import com.endproject.service.CovidProvinceService;
import com.endproject.service.CovidTotalService;
import com.endproject.service.CovidTrendService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：乃王木木
 * @date 2022/12/14 15:52
 */
@Component
public class CovidDataScheduleTask {

    @Autowired
    private CovidTrendService covidTrendService;
    @Autowired
    private CovidTotalService covidTotalService;
    @Autowired
    private CovidProvinceService covidProvinceService;


    /**
     * 获取疫情数据
     * 定时任务，每日执行一次
     * @param
     */
    @Scheduled(fixedDelay = 86400000)/*86400000*/
    public void updateCovidData() throws Exception {
        String host = "https://ncovdata.market.alicloudapi.com";
        String path = "/ncov/cityDiseaseInfoWithTrend";
        String method = "GET";
        String appcode = "682d6c55637a4fcca99f451c29532bf5";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> queries = new HashMap<String, String>();

        try {
        HttpResponse response = HttpUti.doGet(host, path, method, headers, queries);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity,"utf-8");

            JSONObject jsonObject = JSONObject.parseObject(s);
            //一级数据

            Object country = jsonObject.getJSONObject("country");
            Object dataSourceUpdateTime = jsonObject.getJSONObject("dataSourceUpdateTime");
            Object trend = jsonObject.getJSONArray("trend");
            Object provinceArray = jsonObject.getJSONArray("provinceArray");



            //国内总数据
            CovidTotal covidTotal = new CovidTotal();
            JSONObject couObject = JSONObject.parseObject(country.toString());
            JSONObject updateTimeObject = JSONObject.parseObject(dataSourceUpdateTime.toString());
            //获取串内
            Object totalCured = couObject.get("totalCured");
            Object totalDeath = couObject.get("totalDeath");
            Object incDoubtful = couObject.get("incDoubtful");
            Object childStatistic = couObject.get("childStatistic");
            Object totalDoubtful = couObject.get("totalDoubtful");
            Object totalConfirmed = couObject.get("totalConfirmed");
            Object updateTime = updateTimeObject.get("updateTime");
            Object dataSource = updateTimeObject.get("dataSource");

            //赋值
            covidTotal.setTotalCured(totalCured.toString());
            covidTotal.setTotalDeath(totalDeath.toString());
            covidTotal.setIncDoubtful(incDoubtful.toString());
            covidTotal.setChildStatistic(childStatistic.toString());
            covidTotal.setTotalDoubtful(totalDoubtful.toString());
            covidTotal.setTotalConfirmed(totalConfirmed.toString());
            covidTotal.setUpdateTime(updateTime.toString());
            covidTotal.setDataSource(dataSource.toString());
            //总数据插入数据库
            covidTotalService.saveOrUpdate(covidTotal);
            //System.out.println("总数据"+covidTotal);

            //趋势
            Object[] trendObjects = ((JSONArray) trend).toArray();
            List<CovidTrend> trendList = new ArrayList<>();
            for (int i=1;i< trendObjects.length;++i){
                CovidTrend covidTrend = new CovidTrend();
                JSONObject jsonObject1 = JSONObject.parseObject(trendObjects[i].toString());
                //获取
                Object day = jsonObject1.get("day");
                Object sure_cnt = jsonObject1.get("sure_cnt");
                Object die_cnt = jsonObject1.get("die_cnt");
                Object cure_cnt = jsonObject1.get("cure_cnt");
                Object doubt_cnt = jsonObject1.get("sure_cnt");
                Object full_day = jsonObject1.get("fullDay");

                //赋值
                covidTrend.setId(i);
                covidTrend.setDay(day.toString());
                covidTrend.setCure_cnt(sure_cnt.toString());
                covidTrend.setDie_cnt(die_cnt.toString());
                covidTrend.setSure_cnt(cure_cnt.toString());
                covidTrend.setDoubt_cnt(doubt_cnt.toString());
                covidTrend.setFull_day(full_day.toString());

                trendList.add(covidTrend);


            }
            //趋势插入数据库
            covidTrendService.saveOrUpdateBatch(trendList);


            //省市数据
            Object[] proObjects = (((JSONArray) provinceArray).toArray());
            List<CovidProvince> provinceList = new ArrayList<>();
            for (int j=1;j<proObjects.length;j++){
                CovidProvince covidProvince = new CovidProvince();
                JSONObject proObject = JSONObject.parseObject(proObjects[j].toString());
                Object totalCuredPro = proObject.get("totalCured");
                Object totalDeathPro = proObject.get("totalDeath");
                Object childStatisticPro = proObject.get("childStatistic");
                Object totalConfirmedPro = proObject.get("totalConfirmed");

                //赋值
                //covidProvince.setId(j);
                covidProvince.setTotalCured(totalCuredPro.toString());
                covidProvince.setTotalDeath(totalDeathPro.toString());
                covidProvince.setChildStatistic(childStatisticPro.toString());
                covidProvince.setTotalConfirmed(totalConfirmedPro.toString());

                provinceList.add(covidProvince);
            }

            //省市数据添加到数据库
            covidProvinceService.saveOrUpdateBatch(provinceList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
