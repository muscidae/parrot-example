package com.muscidae.parrot.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableDiscoveryClient
@EnableFeignClients( basePackages = {"com.muscidae.parrot.rpc"} )
@MapperScan( basePackages = {"com.muscidae.parrot.example.mapper"} )
@SpringBootApplication(
    exclude = { HibernateJpaAutoConfiguration.class },
    scanBasePackages = {
        "com.muscidae.parrot.rpc", "com.muscidae.parrot.common.basic",
        "com.muscidae.parrot.common.bean.swagger",
        "com.muscidae.parrot.example"
    }
)
public class ParrotExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParrotExampleApplication.class, args);
    }

}

