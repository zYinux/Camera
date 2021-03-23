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
    SUCCESS("0");

    private String code;
}
