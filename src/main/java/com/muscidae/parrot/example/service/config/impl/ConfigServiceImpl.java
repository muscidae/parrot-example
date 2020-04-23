package com.muscidae.parrot.example.service.config.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muscidae.parrot.example.entity.po.config.Config;
import com.muscidae.parrot.example.mapper.config.ConfigMapper;
import com.muscidae.parrot.example.service.config.IConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  系统配置信息 服务实现类
 * </p>
 *
 * @author muscidae
 * @since 2019-02-08
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
