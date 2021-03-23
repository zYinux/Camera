package com.againstsky.camera.core.annotation;

import java.lang.annotation.*;

/**
 * NoAuthInterface
 *
 * @author zyinux
 * @Desc 用于表示不需要登入的接口
 * @date 2021/3/23
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAuthInterface {
}
