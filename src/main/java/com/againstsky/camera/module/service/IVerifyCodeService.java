package com.againstsky.camera.module.service;

/**
 * IVerifyCodeService
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
public interface IVerifyCodeService {

    /**
     * 发送登入验证码
     * @param phone 手机号码
     * @return
     */
    boolean sendLoginVerifyCode(String phone);

    /**
     * 获取当前手机的登入验证码
     * @param phone
     * @return
     */
    String getLoginVerifyCodeByPhone(String phone);
}
