/*
package com.endproject.test.Util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.endproject.entity.UserType;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/22 18:11
 *//*

public class TestPOI {

    //查询所有记录
    public List<UserType> getUsers(){
        List<UserType> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserType userType = new UserType();
            userType.setId(Integer.valueOf(i));
            userType.setSnum("1900102060_"+i);
            userType.setUsername("吴_"+i);
            userType.setDorm_address("6B");
            userType.setGender("2019");
            users.add(userType);
        }
        return users;
    }

    @Test
    public void testExport() throws IOException {
        //获取数据
        List<UserType> users = getUsers();
        //导出数据
        */
/**
        * @Description: 参数1.exportParams 导出配置对象
         *              参数2.导出的类型
         *              参数3.导出数据集合
        * @date 2023/2/22 18:24
        * @author WangNaiLinn
        **//*

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息列表","用户信息"),UserType.class,users);
        FileOutputStream outputStream = new FileOutputStream("E:\\IdeaProjects\\EndProject\\document\\Excel\\11.xls");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

}
*/
