/*
package com.endproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.endproject.Model.vo.DepartmentVo;
import com.endproject.entity.Classe;
import com.endproject.entity.Department;
import com.endproject.entity.Major;
import com.endproject.service.ClasseService;
import com.endproject.service.DepartmentService;
import com.endproject.service.MajorService;
import com.endproject.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

*/
/**
 * @author WangNaiLinn
 * @Description:院系专业班级增删改查
 * @date create in 2023/2/19 7:21
 *//*

@RestController
@RequestMapping("/cdm")
@Slf4j
@Api(value = "院专班接口")
public class CDMController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    MajorService majorService;
    @Autowired
    ClasseService classeService;

    @ApiOperation("初始院系下拉框")
    @GetMapping("/listAllDepartment")
    public ApiResult<Object> listAllDepartment(){
        List<Department> list = departmentService.list();

        return ApiResult.success(list);
    }
    @ApiOperation("初始专业下拉框")
    @GetMapping("/listAllMajro")
    public ApiResult<Object> listAllMajro(){
        List<Major> list = majorService.list();
        return ApiResult.success(list);
    }
    @ApiOperation("初始班级下拉框")
    @GetMapping("/listAllClasse")
    public ApiResult<Object> listAllClasse(){
        List<Classe> list = classeService.list();
        return ApiResult.success(list);
    }

    */
/**
    * @Description:院系专业班级管理
    * @date 2023/2/20 17:33
    * @author WangNaiLinn
    **//*


    @ApiOperation("获取院系")
    @GetMapping("/getDepartment")
    public ApiResult<Object> getDepartment(){
        return ApiResult.success(departmentService.page(new Page<>(1,2)));
    }

    @ApiOperation("添加院系")
    @PostMapping("/addDepartment")
    public ApiResult<Object> addDepartment(Department department){
        departmentService.save(department);
        return ApiResult.success();
    }

    @ApiOperation("修改院系")
    @PostMapping("/editDepartment")
    public ApiResult<Object> editDepartment(@RequestBody Department department){
        departmentService.updateById(department);
        return null;
    }

    @ApiOperation("删除院系")
    @PostMapping("/delDepartment/{departmentId}")
    public ApiResult<Object> delDepartment(@PathVariable Department departmentId){
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",departmentId);
        departmentService.remove(queryWrapper);
        return null;
    }
    @ApiOperation("批量删除院系")
    @PostMapping("/delDepartments")
    public ApiResult<Object> delDepartmentByIdList(@PathVariable List<Department> departmentIds){

        return null;
    }

    @ApiOperation("获取专业")
    @GetMapping("/getMajor")
    public ApiResult<Object> getMajor(){

        return null;
    }

    @ApiOperation("添加专业")
    @PostMapping("/addMajor")
    public ApiResult<Object> addMajor(@RequestBody Major major){

        return null;
    }

    @ApiOperation("修改专业")
    @PostMapping("/editMajor")
    public ApiResult<Object> editMajor(@PathVariable Major majorId){

        return null;
    }

    @ApiOperation("删除专业")
    @PostMapping("/delMajor")
    public ApiResult<Object> delMajor(@PathVariable Major majorId){

        return null;
    }
    @ApiOperation("批量删除专业")
    @PostMapping("/delMajors")
    public ApiResult<Object> delMajorByIdList(@PathVariable List<Major> majorId){

        return null;
    }


    @ApiOperation("获取班级")
    @GetMapping("/getClasse")
    public ApiResult<Object> getClasse(){

        return null;
    }

    @ApiOperation("添加班级")
    @PostMapping("/addClasse")
    public ApiResult<Object> addClasse(){

        return null;
    }

    @ApiOperation("修改班级")
    @PostMapping("/editClasse")
    public ApiResult<Object> editClasse(){

        return null;
    }

    @ApiOperation("删除班级")
    @PostMapping("/delClasse")
    public ApiResult<Object> delClasse(){

        return null;
    }
    @ApiOperation("批量删除班级")
    @PostMapping("/delClasses")
    public ApiResult<Object> delClasseByIdList(){

        return null;
    }
}
*/
