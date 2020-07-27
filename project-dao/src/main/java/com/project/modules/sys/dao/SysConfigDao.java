/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.project.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.modules.sys.entity.SysConfigEntity;
import com.project.modules.sys.vo.info.SysConfigInfoVo;
import com.project.modules.sys.vo.list.SysConfigListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统配置信息
 *
 * @author liangyuding
 * @date 2020-03-19
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {

    /**
     * 校验配置名称是否存在
     * @param name
     * @param configId
     * @return
     */
    Integer checkNameRepeat(@Param("name") String name, @Param("configId") Long configId);

    /**
     * 校验配置编码是否存在
     * @param code
     * @param configId
     * @return
     */
    Integer checkCodeRepeat(@Param("code") String code, @Param("configId") Long configId);

    /**
     * 分页查询系统配置信息
     * @param page
     * @return
     */
    List<SysConfigListVo> queryPage(Page<SysConfigListVo> page);

    /**
     * 根据configId获取系统配置信息
     * @param configId
     * @return
     */
    SysConfigInfoVo info(@Param("configId") Long configId);

    /**
     * 根据configId获取系统配置对象
     * @param configId
     * @return
     */
    SysConfigEntity getSysConfigEntityById(@Param("configId") Long configId);
}
