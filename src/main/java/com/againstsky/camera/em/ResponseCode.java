package com.againstsky.camera.em;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ResponseCode
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("0","success"),
    ERROR("999","UNKNOW_ERROR"),

    AUTH_ERROR("403","用户会话已过期或尚未登录，请重新登录！")
    ;

    private String code;
    private String msg;
}
