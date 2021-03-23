package com.againstsky.camera.module.service.impl;

import com.againstsky.camera.module.service.IVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * VerifyCodeServiceImpl
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Override
    public boolean sendLoginVerifyCode(String phone) {
        return true;
    }

    @Override
    public String getLoginVerifyCodeByPhone(String phone) {
        return "1234";
    }
}
