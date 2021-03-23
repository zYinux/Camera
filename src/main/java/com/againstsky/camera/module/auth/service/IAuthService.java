package com.againstsky.camera.module.auth.service;

import com.againstsky.camera.vo.LoginVO;

/**
 * IAuthService
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
public interface IAuthService {

    /**
     * 验证码登入或者注册
     * @param phone
     * @param code
     * @return
     */
    LoginVO loginOrRegister(String phone,String code);

}
