package com.endproject.controller;

import com.endproject.dao.MsgInfoDao;
import com.endproject.dao.SessionListDao;
import com.endproject.entity.MsgInfo;
import com.endproject.entity.SessionList;
import com.endproject.util.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/30 6:53
 */
@RestController
public class MsgInfoController {
    @Autowired
    private MsgInfoDao msgInfoDao;

    @Autowired
    private SessionListDao sessionListDao;

    // 消息列表
    @GetMapping("/msgList")
    public ApiResult<?> msgList(@RequestParam Integer sessionId){
        SessionList sessionList = sessionListDao.selectByPrimaryKey(sessionId);
        if(sessionList == null){
            return ApiResult.success();
        }
        String fromUserId = sessionList.getUserId();
        String toUserId = sessionList.getToUserId();
        List<MsgInfo> msgInfoList = msgInfoDao.selectMsgList(fromUserId,toUserId);
        // 更新消息已读
        msgInfoDao.msgRead(fromUserId, toUserId);
        // 更新会话里面的未读消息
        sessionListDao.delUnReadCount(fromUserId, toUserId);
        return ApiResult.success(msgInfoList);
    }


}
