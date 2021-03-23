package com.againstsky.camera.eh;

import com.againstsky.camera.object.CameraException;
import com.againstsky.camera.object.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ExceptionHandle
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e){
        System.out.println("未知异常！原因是:"+e);
        return e.getMessage();
    }

    @ExceptionHandler(value = CameraException.class)
    public ResponseResult exceptionHandler(CameraException e){
        return ResponseResult.success(e.getMessage());
    }
}
