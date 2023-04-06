package com.endproject.util;


import com.endproject.component.WebSocket;
import com.endproject.entity.UserType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 统一管理session、websocket、curUser
 */
/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/30 6:46
 */
public class CurPool {
    //    public static CopyOnWriteArraySet<WebSocket> webSockets =new CopyOnWriteArraySet<>();
    public static Map<String, WebSocket> webSockets = new ConcurrentHashMap<>();
    // list 里面第一个存sessionId，第二个存session
    public static Map<String, List<Object>> sessionPool = new ConcurrentHashMap<>();
    // 当前登录用户x
    public static Map<String, UserType> curUserPool = new ConcurrentHashMap<>();
}
