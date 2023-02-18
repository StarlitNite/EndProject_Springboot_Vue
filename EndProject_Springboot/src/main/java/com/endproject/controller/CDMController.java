package com.endproject.controller;

import com.endproject.entity.Department;
import com.endproject.entity.Major;
import com.endproject.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:院系专业班级增删改查
 * @date create in 2023/2/19 7:21
 */
@RestController
@RequestMapping("/cdm")
@Slf4j
@Api(value = "院专班接口")
public class CDMController {

    @ApiOperation("获取院系")
    @GetMapping("/getDepartment")
    public ApiResult<Object> getDepartment(){

        return null;
    }

    @ApiOperation("添加院系")
    @PostMapping("/addDepartment")
    public ApiResult<Object> addDepartment(){

        return null;
    }

    @ApiOperation("修改院系")
    @PostMapping("/editDepartment")
    public ApiResult<Object> editDepartment(@PathVariable Department departmentId){

        return null;
    }

    @ApiOperation("删除院系")
    @PostMapping("/delDepartment")
    public ApiResult<Object> delDepartment(@PathVariable Department departmentId){

        return null;
    }
    @ApiOperation("批量删除院系")
    @PostMapping("/delDepartments")
    public ApiResult<Object> delDepartmentByIdList(@PathVariable List<Department> departmentId){

        return null;
    }

    @ApiOperation("获取专业")
    @GetMapping("/getMajor")
    public ApiResult<Object> getMajor(){

        return null;
    }

    @ApiOperation("添加专业")
    @PostMapping("/addMajor")
    public ApiResult<Object> addMajor(){

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
