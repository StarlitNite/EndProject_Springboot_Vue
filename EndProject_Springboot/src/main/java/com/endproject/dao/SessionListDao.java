package com.endproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.endproject.entity.SessionList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/30 6:28
 */
@Mapper
public interface SessionListDao extends BaseMapper<SessionList> {
    int deleteByPrimaryKey(Integer id);

    int insert(SessionList sessionList);

    int insertSelective(SessionList sessionList);

    SessionList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SessionList sessionList);

    int updateByPrimaryKey(SessionList sessionList);

    List<String> selectUserIdByUserId(String id);

    List<SessionList> selectByUserId(String id);

    String selectIdByUser(@Param("fromId") String fromId, @Param("toId") String toId);

    SessionList select(SessionList sessionList);

    void addUnReadCount(@Param("userId") String userId,@Param("toUserId") String toUserId);

    void delUnReadCount(@Param("fromUserId") String fromUserId,@Param("toUserId") String toUserId);
}
