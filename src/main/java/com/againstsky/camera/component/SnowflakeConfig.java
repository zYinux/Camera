package com.againstsky.camera.component;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * SnowflakeConfig
 *
 * @author zyinux
 * @Desc
 * @date 2021/3/22
 */
@Component
public class SnowflakeConfig {

    @Bean
    public Snowflake snowflake(){
        return IdUtil.createSnowflake(1, 1);
    }
}
