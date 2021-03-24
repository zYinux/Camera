package com.againstsky.camera.vo;

import com.againstsky.camera.dao.entity.MemberEntity;
import lombok.Data;

/**
 * LoginVO
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@Data
public class LoginVO {

    String token;

    MemberEntity memberEntity;
}
