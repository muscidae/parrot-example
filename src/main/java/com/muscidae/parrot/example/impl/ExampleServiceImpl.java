package com.muscidae.parrot.example.impl;

import com.muscidae.parrot.common.entity.result.ApiResult;
import com.muscidae.parrot.common.standard.example.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author muscidae
 * @date 2020/4/15 18:39
 * @copyright ©2020
 */
@Slf4j
@RestController
public class ExampleServiceImpl implements ExampleService {

    @Override
    public ApiResult<?> remoteCall() {
        // TODO
        log.info("remote procedure call success");
        return ApiResult.success("remote procedure call success");
    }

}
