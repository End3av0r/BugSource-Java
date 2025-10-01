package org.huawei.com.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.huawei.com.infrastructure.dao")
public class InfrastructureMybatisConfig {
}
