package com.againstsky.camera.object;

import com.againstsky.camera.em.ResponseCode;
import lombok.Data;

/**
 * ResponseResult
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@Data
public class ResponseResult<T> {

    public static final String SUCCESS_MSG = "success";

    public ResponseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 状态
     */
    String code;

    String message;

    T data;

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), SUCCESS_MSG, data);
    }

}
