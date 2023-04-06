package com.endproject.controller;

import com.endproject.dao.SessionListDao;
import com.endproject.dao.UserDao;
import com.endproject.entity.SessionList;
import com.endproject.entity.UserType;
import com.endproject.util.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/30 6:55
 */
@RestController
public class SessionController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionListDao sessionListDao;

    /**
     * 已建立会话
     */
    @GetMapping("/sessionList/already")
    public ApiResult<?> sessionListAlready(@RequestParam String id){
        List<SessionList> sessionLists = sessionListDao.selectByUserId(id);
        return  ApiResult.success(sessionLists);
    }

    // 可建立会话的用户
    @GetMapping("/sessionList/not")
    public ApiResult<?> sessionListNot(@RequestParam(value = "") String id){
        List<String> list = sessionListDao.selectUserIdByUserId(id);
        list.add(id);
        List<UserType> cloudList = userDao.getCloudList(list);
        return ApiResult.success(cloudList);
    }

    // 创建会话
    @GetMapping("/createSession")
    public ApiResult<?> createSession(@RequestParam String id,@RequestParam String tosnum,@RequestParam String toUserName){
        SessionList sessionList = new SessionList();
        sessionList.setUserId(id);
        sessionList.setUnReadCount(0);
        sessionList.setListName(toUserName);
        sessionList.setToUserId(tosnum);
        sessionListDao.insert(sessionList);
        // 判断对方和我建立会话没有？ 没有也要建立
        String integer = sessionListDao.selectIdByUser(tosnum, id);
        if (integer == null || integer.length() <= 0){
            UserType user = userDao.selectByPrimaryKey(id);
            sessionList.setUserId(tosnum);
            sessionList.setToUserId(id);
            sessionList.setListName(user.getUsername());
            sessionListDao.insert(sessionList);
        }
        return ApiResult.success();
    }

    // 删除会话
    @GetMapping("/delSession")
    public ApiResult<?> delSession(@RequestParam Integer sessionId){
        sessionListDao.deleteByPrimaryKey(sessionId);
        return ApiResult.success();
    }
}
