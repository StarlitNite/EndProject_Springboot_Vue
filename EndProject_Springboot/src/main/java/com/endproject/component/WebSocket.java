package com.endproject.component;

import com.alibaba.fastjson.JSON;
import com.endproject.dao.MsgInfoDao;
import com.endproject.dao.SessionListDao;
import com.endproject.dao.UserDao;
import com.endproject.entity.MsgInfo;
import com.endproject.entity.SessionList;
import com.endproject.entity.UserType;
import com.endproject.util.CurPool;
import com.endproject.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/30 15:45
 */
@Component
@ServerEndpoint("/websocket/{snum}/{sessionId}")
public class WebSocket {

    @Autowired
    private SessionListDao sessionListDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MsgInfoDao msgInfoDao;

    private Session session;


    @OnOpen
    public void onOpen(Session session, @PathParam(value="snum")String snum, @PathParam(value="sessionId")Integer sessionId) {
        System.out.println("onOpen");
        this.session = session;
        CurPool.webSockets.put(snum,this);
        List<Object> list = new ArrayList<>();
        list.add(sessionId);
        list.add(session);
        CurPool.sessionPool.put(snum , list);
        System.out.println("【websocket消息】有新的连接，总数为:"+CurPool.webSockets.size());
    }

    @OnClose
    public void onClose() {
        System.out.println("onClose");
        // 断开连接删除用户删除session
        String userId = String.valueOf(this.session.getRequestParameterMap().get("snum").get(0));
        CurPool.sessionPool.remove(userId);
        CurPool.webSockets.remove(userId);
        if (userDao == null){
            this.userDao = (UserDao) SpringContextUtil.getBean("userDao");
        }
        UserType user = userDao.selectByPrimaryKey(userId);
        CurPool.curUserPool.remove(user.getUsername());
        System.out.println("【websocket消息】连接断开，总数为:"+CurPool.webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("onMessage");
        String sessionId = this.session.getRequestParameterMap().get("sessionId").get(0);
        if (sessionId == null){
            System.out.println("sessionId 错误");
        }
        // 在这里无法注入Mapper所以使用这种方式注入Mapper
        if (sessionListDao == null){
            this.sessionListDao = (SessionListDao) SpringContextUtil.getBean("SessionListDao");
        }
        if (userDao == null){
            this.userDao = (UserDao)SpringContextUtil.getBean("UserDao");
        }
        if (msgInfoDao == null){
            this.msgInfoDao = (MsgInfoDao)SpringContextUtil.getBean("MsgInfoDao");
        }
        SessionList sessionList = sessionListDao.selectByPrimaryKey(Integer.parseInt(sessionId));
        UserType user = userDao.selectByPrimaryKey(sessionList.getUserId());
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setContent(message);
        msgInfo.setCreateTime(new Date());
        msgInfo.setFromUserId(sessionList.getUserId());
        msgInfo.setFromUserName(user.getUsername());
        msgInfo.setToUserId(sessionList.getToUserId());
        msgInfo.setToUserName(sessionList.getListName());
        msgInfo.setUnReadFlag(0);
        // 消息持久化
        msgInfoDao.insert(msgInfo);

        // 判断用户是否存在，不存在就结束
        List<Object> list = CurPool.sessionPool.get(sessionList.getToUserId());
        if (list == null || list.isEmpty()){
            // 用户不存在，更新未读数
            sessionListDao.addUnReadCount(sessionList.getToUserId(),sessionList.getUserId());
        }else{
            // 用户存在，判断会话是否存在
            String id = sessionListDao.selectIdByUser(sessionList.getToUserId(), sessionList.getUserId())+"";
            String o = list.get(0) + "";
            if (id.equals(o)){
                // 会话存在直接发送消息
                sendTextMessage(sessionList.getToUserId(), (String) JSON.toJSON(msgInfo));
            }else {
                // 判断会话列表是否存在
                if (id == null || "".equals(id) || "null".equals(id)){
                    // 新增会话列表
                    SessionList tmpSessionList = new SessionList();
                    tmpSessionList.setUserId(sessionList.getToUserId());
                    tmpSessionList.setToUserId(sessionList.getUserId());
                    tmpSessionList.setListName(user.getUsername());
                    tmpSessionList.setUnReadCount(1);
                    sessionListDao.insert(tmpSessionList);
                }else {
                    // 更新未读消息数量
                    sessionListDao.addUnReadCount(sessionList.getToUserId(),sessionList.getUserId());
                }
                // 会话不存在发送列表消息
                List<SessionList> sessionLists = sessionListDao.selectByUserId(sessionList.getToUserId());
                sendTextMessage(sessionList.getToUserId() ,(String) JSON.toJSON(sessionLists));
            }
        }
        System.out.println("【websocket消息】收到客户端消息:"+message);
    }

    // 此为广播消息
//    public void sendAllMessage(String message) {
//        for(WebSocket webSocket : webSockets) {
//            System.out.println("【websocket消息】广播消息:"+message);
//            try {
//                webSocket.session.getAsyncRemote().sendText(message);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    // 此为单点消息 (发送文本)
    public void sendTextMessage(String userId, String message) {
        Session session = (Session)CurPool.sessionPool.get(userId).get(1);
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息 (发送对象)
//    public void sendObjMessage(String sessionId, Object message) {
//        Session session = CurPool.sessionPool.get(sessionId);
//        if (session != null) {
//            try {
////                session.getAsyncRemote().sendObject(message);
//                session.getBasicRemote().sendText(JsonUtils.objectToJson(message));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

}