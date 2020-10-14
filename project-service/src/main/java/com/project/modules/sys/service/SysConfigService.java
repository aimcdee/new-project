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

package com.project.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.modules.sys.entity.SysConfigEntity;
import com.project.modules.sys.vo.info.SysConfigInfoVo;
import com.project.modules.sys.vo.save.SysConfigSaveVo;
import com.project.modules.sys.vo.update.SysConfigUpdateVo;
import com.project.utils.PageUtils;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author liangyuding
 * @date 2020-03-19
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    /**
     * 校验配置名称是否存在
     * @param name
     * @param configId
     */
    void checkNameRepeat(String name, Long configId);

    /**
     * 校验配置编码是否存在
     * @param code
     * @param configId
     */
    void checkCodeRepeat(String code, Long configId);

    /**
     * 分页查询系统配置信息
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增系统配置
     * @param config
     * @param sysUserId
     */
    void saveEntity(SysConfigSaveVo config, Long sysUserId);

    /**
     * 根据系统配置ID获取系统配置信息
     * @param configId
     * @return
     */
    SysConfigInfoVo info(Long configId);

    /**
     * 修改系统配置
     * @param config
     * @param sysUserId
     */
    void updateSysConfigUpdateVo(SysConfigUpdateVo config, Long sysUserId);

    /**
     * 修改系统配置状态
     * @param configId
     * @param status
     * @param sysUserId
     */
    void changeStatus(Long configId, Integer status, Long sysUserId);

    /**
     * 获取配置好的默认Value
     * @param code
     * @return
     */
    String getDefaultValue(String code);
}
