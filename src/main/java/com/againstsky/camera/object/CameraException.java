package com.againstsky.camera.object;

import lombok.Data;

/**
 * CameraException
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@Data
public class CameraException extends RuntimeException {

    private String errorCode = "999";

    public CameraException() {

    }

    public CameraException(String message) {
        super(message);
    }
}
