package com.project.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门Entity
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Data
@Accessors(chain = true)
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {
    private static final long serialVersionUID = -7235900675462899576L;

    /**部门ID*/
    @TableId
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**部门名称*/
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**上级ID,没有上级填写0*/
    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    /**状态 0.禁用 1.正常*/
    @ApiModelProperty(value = "状态 0.禁用 1.正常")
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
