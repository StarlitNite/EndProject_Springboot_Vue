package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.MsgInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/30 6:45
 */
@Mapper
public interface MsgInfoDao extends BaseMapper<MsgInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgInfo msgInfo);

    int insertSelective(MsgInfo msgInfo);

    MsgInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgInfo msgInfo);

    int updateByPrimaryKey(MsgInfo msgInfo);

    List<MsgInfo> select(MsgInfo msgInfo);

    void msgRead(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);

    List<MsgInfo> selectMsgList(@Param("fromUserId") String fromUserId,@Param("toUserId") String toUserId);
}
