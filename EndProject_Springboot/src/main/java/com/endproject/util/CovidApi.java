package com.endproject.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：乃王木木
 * @date 2022/12/9 15:57
 */

public class CovidApi {
    /*public static void main(String[] args) {
        String host = "https://ncovdata.market.alicloudapi.com";
        String path = "/ncov/cityDiseaseInfoWithTrend";
        String method = "GET";
        String appcode = "682d6c55637a4fcca99f451c29532bf5";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();


        try {
            HttpResponse response = HttpUti.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity,"utf-8");
            System.out.println(s);
            JSONObject jsonObject = JSONObject.parseObject(s);
//            Object data = jsonObject.get("data");
            Object Country = jsonObject.get("country");
            Object dataSourceUpdateTime = jsonObject.get("dataSourceUpdateTime");
            Object trend = jsonObject.get("trend");
            Object provinceArray = jsonObject.get("provinceArray");
            System.out.println("Country:"+Country);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
