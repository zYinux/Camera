package com.againstsky.camera.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    /**
     * token的Http Request Header的key
     */
    private String tokenHeaderKey;
    /**
     * token在过期之前，但是已经需要被刷新时，response返回的header信息的key。
     */
    private String refreshedTokenHeaderKey;

    /**
     * token 加密用的密钥，该值的长度最少10个字符(过短会报错)。
     */
    private String tokenSigningKey;

    /**
     * 令牌的过期时间，单位毫秒
     */
    private Long expiration;
}
