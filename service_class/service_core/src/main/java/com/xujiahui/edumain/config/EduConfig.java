package com.xujiahui.edumain.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 个性化配置文件
 *
 */
@Configuration
@MapperScan("com.xujiahui.edumain.mapper")

public class EduConfig {

    /**
     * 逻辑删除.就是假删除,数据库里还有,就是让你查询不到
     *  配置com.baomidou.mybatisplus.core.config.GlobalConfig$DbConfig
     *  实体类字段上加上@TableLogic注解
     */

    /**
     * 分页插件 --->mybatis plus ホームページ
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}

