package com.againstsky.camera.controller;

import com.againstsky.camera.module.auth.service.IAuthService;
import com.againstsky.camera.object.ResponseResult;
import com.againstsky.camera.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    @RequestMapping("login")
    public ResponseResult<LoginVO> login(@RequestParam String phone,
                                         @RequestParam String code){
        LoginVO loginVO = authService.loginOrRegister(phone, code);
        return ResponseResult.success(loginVO);
    }

}
