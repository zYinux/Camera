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
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), data);
    }

    public static  ResponseResult error(ResponseCode responseCode){
        return new ResponseResult(responseCode.getCode(), responseCode.getMsg(), null);
    }

    public static <T> ResponseResult<T> error(T data){
        return new ResponseResult<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), data);
    }

    public static <T> ResponseResult<T> error(String code,String msg,T data){
        return new ResponseResult<>(code, msg, data);
    }
    public static  ResponseResult error(String code,String msg){
        return new ResponseResult(code, msg, null);
    }

}
