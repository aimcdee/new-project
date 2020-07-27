package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置Entity
 *
 * @author liangyuding
 * @date 2020-03-19
 */
@Data
@Accessors(chain = true)
@TableName("sys_config")
public class SysConfigEntity implements Serializable {
    private static final long serialVersionUID = -4733020260838512672L;

    /**编号*/
    @TableId
    @ApiModelProperty(value = "编号")
    private Long configId;

    /**配置名称*/
    @ApiModelProperty(value = "配置名称")
    private String name;

    /**配置编码*/
    @ApiModelProperty(value = "配置编码")
    private String code;

    /**配置值*/
    @ApiModelProperty(value = "配置值")
    private String value;

    /**配置显示值*/
    @ApiModelProperty(value = "配置显示值")
    private String memo;

    /**状态 0.禁用 1.启用*/
    @ApiModelProperty(value = "状态 0.禁用 1.启用")
    private Integer status;

    /**创建者ID*/
    @ApiModelProperty(value = "创建者ID")
    private Long createUserId;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**最后操作人ID*/
    @ApiModelProperty(value = "最后操作人ID")
    private Long updateUserId;

    /**最后操作时间*/
    @ApiModelProperty(value = "最后操作时间")
    private Date updateTime;
}