package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.endproject.Model.vo.LeaveVo;
import com.endproject.entity.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/9 16:21
 */
@Mapper
public interface LeaveDao extends BaseMapper<Leave> {

    List<Leave> getLeaveByCou(String snum);

    List<Leave> getLeaveByStu(String snum);


    //条件 搜snum，正常的话，无条件搜索显示所有 页数，条件(单独搜索时再使用)，snum;
//    IPage<LeaveVo> getLeaveByCou(IPage<LeaveVo> page,,String snum);
}
