package com.againstsky.camera.module.auth.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.againstsky.camera.config.AppConfig;
import com.againstsky.camera.dao.entity.MemberEntity;
import com.againstsky.camera.dao.mapper.IMemberMapper;
import com.againstsky.camera.em.ResponseCode;
import com.againstsky.camera.em.TokenPayload;
import com.againstsky.camera.module.auth.service.IAuthService;
import com.againstsky.camera.module.service.IVerifyCodeService;
import com.againstsky.camera.object.CameraException;
import com.againstsky.camera.util.JwtUtil;
import com.againstsky.camera.vo.LoginVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * AuthServiceImpl
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    IMemberMapper memberMapper;

    @Autowired
    IVerifyCodeService verifyCodeService;

    @Autowired
    Snowflake snowflake;

    @Autowired
    AppConfig appConfig;

    @Override
    public LoginVO loginOrRegister(String phone, String code) {

        //拿取验证码判断是否正确
        String verifyCode = verifyCodeService.getLoginVerifyCodeByPhone(phone);
        if (!Objects.equals(verifyCode, code)) {
            throw new CameraException("1", "验证码错误，请重新输入");
        }


        //检查当前手机号是否存在
        MemberEntity member = memberMapper.selectOne(new QueryWrapper<MemberEntity>()
                .lambda().eq(MemberEntity::getPhone, phone));
        if (member != null) {
            //用户已经注册了
            //生成登入信息返回
            return generatorLoginInfo(member);
        }
        //没有注册过，给其注册，然后生成信息返回

        member = registerMember(phone);

        return generatorLoginInfo(member);
    }


    private MemberEntity registerMember(String phone) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setPhone(phone);
        memberEntity.setUid(snowflake.nextIdStr());
        memberEntity.setName("小白" + phone.substring(0, 5));
        int insert = memberMapper.insert(memberEntity);
        if (insert != 1) {
            throw new CameraException("注册失败,请稍后重试");
        }
        return memberEntity;
    }

    private LoginVO generatorLoginInfo(MemberEntity memberEntity) {
        Map<String, Object> payload = new HashMap<>(1);
        payload.put(TokenPayload.USER_ID.name(), memberEntity.getUid());
        String token = null;
        try {
            token = JwtUtil.generateToken(payload, appConfig.getExpiration(), appConfig.getTokenSigningKey());
        } catch (Exception e) {
            throw new CameraException(ResponseCode.AUTH_ERROR);
        }
        LoginVO loginVO = new LoginVO();
        loginVO.setMemberEntity(memberEntity);
        loginVO.setToken(token);
        return loginVO;
    }
}
