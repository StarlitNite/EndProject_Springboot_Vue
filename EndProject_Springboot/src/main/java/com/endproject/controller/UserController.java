package com.endproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.endproject.Model.dto.LoginInfo;
import com.endproject.Model.dto.SupeditUserInfo;
import com.endproject.Model.dto.addUserInfo;
import com.endproject.Model.vo.UserVo;
import com.endproject.entity.*;
import com.endproject.service.*;
import com.endproject.util.JwtUtil;
import com.endproject.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 乃王木木
 */
@RestController
@RequestMapping("/user/")
@Slf4j
@Api(value = "用户接口")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ClasseService classeService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    MajorService majorService;
    @Autowired
    CounselorService counselorService;


    /**
    * @Description:登录
    * @date 2022/12/23 12:15
    * @author WangNaiLinn
    **/
    @ApiOperation(value = "登录")
    @GetMapping(value = "login")
    public ApiResult<Object> login(@RequestBody LoginInfo loginInfo){
        log.info("前端消息发过来了:{}", loginInfo);
        //QueryWrapper<UserType> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UserType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserType::getSnum,loginInfo.getSnum())
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
    @GetMapping("getUser")
    public ApiResult<Object> getUser(UserVo userVo){
        IPage<UserType> page = new Page<>(userVo.getPage(),userVo.getLimit());
        QueryWrapper<UserType> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(userVo.getUsername()),("username"),userVo.getUsername());
        queryWrapper.eq(("Snum"),userVo.getSnum());
        IPage<UserType> iPage = userService.page(page, queryWrapper);
        for (UserType userType: iPage.getRecords()){
            //1.班级名赋值
            if (userType.getClasse_id()!=null){
                Classe classe = classeService.getById(userType.getClasse_id());
                userType.setClasse_name(classe.getClasse_name());
            }
            //2.专业名赋值
            if (userType.getMajor_id()!=null){
                Major major = majorService.getById(userType.getMajor_id());
                userType.setMajor_name(major.getMajor());
            }
            //3.系名赋值
            if (userType.getDepartment_id()!=null){
                Department department = departmentService.getById(userType.getDepartment_id());
                department.setDepartment_name(department.getDepartment_name());
            }
            //4.教师名赋值
            if(userType.getCounselor_id()!=null){
                Counselor counselor = counselorService.getById(userType.getCounselor_id());
                counselor.setCounselor_name(counselor.getCounselor_name());
            }
        }
        return ApiResult.success(iPage);
    }

    @ApiOperation("添加用户")
    @PostMapping("addUser")
    public ApiResult<Object> addUser(@RequestBody addUserInfo addUserInfo){
        userService.addUserByInfo(addUserInfo);
        return ApiResult.success("添加用户成功");
    }

    @ApiOperation("超级管理员修改用户")
    @PostMapping("editUser/{userId}")
    public ApiResult<Object> editUser(@PathVariable Integer userId,@RequestBody SupeditUserInfo supeditUserInfo){
        userService.editUserById(userId,supeditUserInfo);
        return ApiResult.success();
    }

    @ApiOperation("学生修改密码")//半成品
    @PostMapping("editpwd/{userId}")
    public ApiResult<Object> editpwd(@PathVariable Integer userId,@RequestBody String password){
        userService.stuupdateByid(userId,password);
        return ApiResult.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("delUser/{userId}")
    public ApiResult<Object> delUser(@PathVariable Integer userId){
        userService.removeById(userId);
        return ApiResult.success();
    }

    @ApiOperation("Excel批量导入用户")
    @PostMapping("ExcelInportUser")
    public ApiResult<UserType> ExcelInportUser(@RequestParam("file") MultipartFile file) throws Exception{
        //1.判断文件不能为空
        if (file.isEmpty()){
            return  ApiResult.error("文件为空！");
        }
        //2.POI获取Excel解析数据
        HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
        HSSFSheet sheet = wb.getSheetAt(0);
        //3.定义集合接收文件数据
        List<UserType> list = new ArrayList<>();
        HSSFRow row = null;
        //4.解析数据，装到集合里
        for (int i = 0;i<sheet.getPhysicalNumberOfRows();i++){
            //4.1定义实体
            UserType userType = new UserType();
            //4.2每一行数据放到实体类中
            row = sheet.getRow(i);
            //4.3解析
            userType.setId((int)row.getCell(0).getNumericCellValue());
            userType.setSnum((int)row.getCell(1).getNumericCellValue());
            userType.setUsername(row.getCell(2).getStringCellValue());
            userType.setPassword(row.getCell(3).getStringCellValue());
            userType.setFamily_address(row.getCell(4).getStringCellValue());
            userType.setDorm_address(row.getCell(5).getStringCellValue());
            userType.setTel(row.getCell(6).getStringCellValue());
            userType.setAge((int)row.getCell(7).getNumericCellValue());
            userType.setGender(row.getCell(8).getStringCellValue());
            userType.setSalt(row.getCell(9).getStringCellValue());
            userType.setStatus((int)row.getCell(10).getNumericCellValue());
            userType.setRole_id((int)row.getCell(11).getNumericCellValue());
            userType.setDepartment_id((int)row.getCell(12).getNumericCellValue());
            userType.setMajor_id((int)row.getCell(13).getNumericCellValue());
            userType.setClasse_id((int)row.getCell(14).getNumericCellValue());
            userType.setCounselor_id((int)row.getCell(14).getNumericCellValue());

            list.add(userType);
        }

        userService.saveBatch(list);
        return null;
    }
}
