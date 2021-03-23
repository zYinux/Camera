package com.againstsky.camera.eh;

import com.againstsky.camera.object.CameraException;
import com.againstsky.camera.object.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionHandle
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public ResponseResult<String> exceptionHandler(Exception e){
        log.error("[位置错误] e:{}",e.getLocalizedMessage());
        e.printStackTrace();
        return ResponseResult.error(e.getMessage());
    }

    @ExceptionHandler(value = CameraException.class)
    public ResponseResult<String> exceptionHandler(CameraException e){
        return ResponseResult.error(e.getErrorCode(),e.getMessage());
    }
}
