package com.muscidae.parrot.example.config.web;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

/**
 * @author muscidae
 * @date 2018/12/15 12:06
 * @copyright ©2019
 * @description WebMVC配置类
 */
@Slf4j
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(stringConverter());
        converters.add(fastConverter());
    }

    private StringHttpMessageConverter stringConverter(){
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(false); // 关闭WriteAcceptCharset
        stringConverter.setDefaultCharset(StandardCharsets.UTF_8);
        return stringConverter;
    }

    private FastJsonHttpMessageConverter fastConverter(){
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
            DisableCircularReferenceDetect,  //消除对同一对象循环引用
            WriteMapNullValue  //输出值为null的字段
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes());
        return fastConverter;
    }

    private List<MediaType> supportedMediaTypes(){
        return List.of(
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_FORM_URLENCODED,
                    MediaType.APPLICATION_PDF,
                    MediaType.APPLICATION_XML,
                    MediaType.IMAGE_GIF,
                    MediaType.IMAGE_JPEG,
                    MediaType.IMAGE_PNG,
                    MediaType.TEXT_XML,
                    MediaType.TEXT_HTML,
                    MediaType.APPLICATION_OCTET_STREAM
        );
    }


}
