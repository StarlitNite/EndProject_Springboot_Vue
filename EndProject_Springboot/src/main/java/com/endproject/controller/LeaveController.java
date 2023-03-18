package com.endproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.endproject.Model.vo.LeaveVo;
import com.endproject.entity.Leave;
import com.endproject.entity.UserType;
import com.endproject.enums.ApprovalEnumStatus;
import com.endproject.service.LeaveService;
import com.endproject.util.ApiResult;
import com.endproject.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/8 22:46
 */
@RestController
@RequestMapping("/leave/")
@Slf4j
@Api(value = "申请审批接口",tags = {"申请审批接口"})
public class LeaveController {
    @Autowired
    LeaveService leaveService;

    @ApiOperation("获取申请列表")
    @GetMapping("getleave")
    public ApiResult<Object> getleave(UserType userType, LeaveVo leaveVo){
        IPage<Leave> page = new Page<>(leaveVo.getPage(),leaveVo.getLimit());
        QueryWrapper<Leave> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(leaveVo.getSnum()),("snum"),leaveVo.getSnum());
        Integer role_id = userType.getRole_id();
        if (role_id.equals(1)){
            IPage<Leave> iPage = leaveService.page(page,queryWrapper);
            return ApiResult.success(iPage);
        }else if (role_id.equals(2)){
            //通过获取   参考vue去写分页
            List<Leave> list = leaveService.getLeaveByCou(userType.getSnum());
            return ApiResult.success(list);
        }else if (role_id.equals(3)){
            List<Leave> list = leaveService.getLeaveByStu(userType.getSnum());
            return ApiResult.success(list);
        }
        /*获取到权限id
        获取教师id为___的学生id为___的请假记录
        获取学生id为
        如果是教师登录，先通过教师id  获取教师id为___的学生id为___的请假记录
        登录判断教师，前端传教师id回后端
        是用户也是教师  教工号
        先根据教工号到教师表中查到教工，再用教工id去user表查询学生，再用学生学号去查询对应的请假表
        后端通过教师id获取都有哪些学生，再通过学生id去请假表里获取对应数据
        学生登录，获取自己学号的请假记录即可
        管理员直接查询全部请假审批表格
        教师获取到属于自己班级的学生的请假记录
        更新后
        SELECT a.id,a.snum,a.reason,a.address,a.`day`,a.phone,a.node_status,a.create_time,a.update_time FROM approval_process as a
        LEFT JOIN `user` ON `user`.snum = a.snum WHERE `user`.counselor_id=
        (SELECT counselor.id FROM counselor LEFT JOIN `user` ON `user`.snum = counselor.cnum WHERE snum=19027355608)
        */
        return null;
    }

    @ApiOperation("创建申请")
    @PostMapping("addleave")
    public ApiResult<Object> addLeave(Leave leave){
        leaveService.save(leave);
        return null;
    }

    @ApiOperation("删除申请")//取消和删除
    @PostMapping("delleave")
    public ApiResult<Object> delLeave(@PathVariable Integer id){
        leaveService.removeById(id);
        return null;
    }


    @ApiOperation("更新状态")
    @PostMapping("updateleave")
    public ApiResult<Object> updateleave(Leave leave, HttpServletRequest request){
        /*判断身份是否为教师  再判断是否为审批状态，然后根据前端传来的数字进行修改 结束*/
        String header = request.getHeader("Authorization");
        Object role_id = JwtUtil.parse(header);

        if((Integer) role_id !=3){
            if (leave.getNode_status()==2){
                leave.setNode_status(ApprovalEnumStatus.APPROVAL_TEACHER_PASSED.hashCode());
                leaveService.updateById(leave);
            }else if (leave.getNode_status()==3){
                leave.setNode_status(ApprovalEnumStatus.TEACHER_REJECTED.hashCode());
                leaveService.updateById(leave);
            }

        }

        return ApiResult.success();
    }

}
