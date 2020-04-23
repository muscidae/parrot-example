package com.muscidae.parrot.example.config.plugin;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.muscidae.parrot.common.util.random.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author muscidae
 * @date 2019/10/29 15:26
 * @copyright ©2019
 * @description 插件配置
 */
@Configuration
public class PluginConfig {

    /**
     * MyBatis Plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 雪花算法ID生成器
     */
    @Bean
    public SnowFlake snowFlake(){
        return new SnowFlake(0, 0);
    }

}
