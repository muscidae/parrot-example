package com.muscidae.parrot.example.controller.example;

import com.muscidae.parrot.common.entity.result.ApiResult;
import com.muscidae.parrot.example.entity.po.config.Config;
import com.muscidae.parrot.example.service.config.IConfigService;
import com.muscidae.parrot.rpc.example.ExampleFeign;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author muscidae
 * @date 2020/4/15 18:51
 * @copyright ©2020
 * @description
 */
@RestController
@AllArgsConstructor
@RequestMapping("/example")
public class ExampleController {

    private final IConfigService configService;
    private final ExampleFeign exampleFeign;


    @ApiOperation(value = "获取系统配置详情", httpMethod = "GET", response = Config.class, notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", paramType = "path", value = "系统配置信息ID", required = true, dataTypeClass = Integer.class),
    })
    @GetMapping("/get/{id}")
    public ApiResult<Config> get(@PathVariable Integer id) {
        return ApiResult.success(configService.getById(id));
    }

    @GetMapping("/remoteCall")
    public ApiResult<?> remoteCall() {
        return exampleFeign.remoteCall();
//        return ApiResult.success();
    }


}
