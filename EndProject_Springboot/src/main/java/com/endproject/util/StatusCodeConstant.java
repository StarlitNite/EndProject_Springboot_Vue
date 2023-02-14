package com.endproject.util;

/**
 * @author：乃王木木
 * @date 2022/12/21 20:58
 */
public interface StatusCodeConstant {
    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 0;

    /**
     * {@code 200 OK} (HTTP/1.0 -RFC 1945)
     * 请求成功，GET、POST
     */
    Integer STATUS_200 = 200;

    /**
     * Found
     */
    Integer STATUS_302 = 302;

    /**
     * Bad Request 客户端请求的语法错误，服务器无法理解
     */
    Integer STATUS_400 = 400;

    /**
     * 401 Unauthorized  请求要求用户的身份认证
     */
    Integer STATUS_401 =401;

    /**
     * 403 Forbidden 服务器拒绝执行此请求
     */
    Integer STATUS_403 = 403;

    /**
     * 404 Not Found 服务器无法根据客户端请求找到资源
     */
    Integer STATUS_404 = 404;

    /**
     * 405 Method Not Allowed  客户端请求中的方法被禁止
     */
    Integer STATUS_405 = 405;

    /**
     * {@code 500 Internal Server Error}
     * 服务器错误，无法完成请求
     */
    Integer STATUS_500 = 500;


}
