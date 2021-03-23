package com.againstsky.camera.object;

import com.againstsky.camera.util.ContextUtil;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * TokenData
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/23
 */
@Data
public class TokenData {

    /**
     * 在HTTP Request对象中的属性键。
     */
    public static final String REQUEST_ATTRIBUTE_NAME = "tokenData";
    /**
     * 用户Id。
     */
    private String userId;

    /**
     * 用户请求过来的token
     */
    private String token;

//    /**
//     * 用户显示名称。
//     */
//    private String showName;
//    /**
//     * 标识不同登录的会话Id。
//     */
//    private String sessionId;

    /**
     * 将令牌对象添加到Http请求对象。
     *
     * @param tokenData 令牌对象。
     */
    public static void addToRequest(TokenData tokenData) {
        HttpServletRequest request = ContextUtil.getHttpRequest();
        request.setAttribute(TokenData.REQUEST_ATTRIBUTE_NAME, tokenData);
    }

    /**
     * 从Http Request对象中获取令牌对象。
     *
     * @return 令牌对象。
     */
    public static TokenData takeFromRequest() {
        HttpServletRequest request = ContextUtil.getHttpRequest();
        return (TokenData) request.getAttribute(REQUEST_ATTRIBUTE_NAME);
    }
}
