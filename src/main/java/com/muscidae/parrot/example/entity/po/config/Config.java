package com.muscidae.parrot.example.entity.po.config;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muscidae.parrot.common.entity.po.IdentityEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统配置信息
 * </p>
 *
 * @author muscidae
 * @since 2019-02-08
 */
@ApiModel(value = "系统配置信息")
@Data
@Accessors(chain = true)
@TableName("sys_config")
@EqualsAndHashCode(callSuper = false)
public class Config extends IdentityEntity<Integer> {

    @ApiModelProperty(value = "Key")
    @TableField("`key`")
    private String key;

    @ApiModelProperty(value = "Value")
    @TableField("`value`")
    private String value;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

}
