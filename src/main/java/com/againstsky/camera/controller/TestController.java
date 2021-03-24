package com.againstsky.camera.controller;

import com.againstsky.camera.object.TokenData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/24
 */
@RestController
public class TestController {

    @PostMapping("/testToken")
    public String testToken(){
        return TokenData.takeFromRequest().getUserId();
    }
}
