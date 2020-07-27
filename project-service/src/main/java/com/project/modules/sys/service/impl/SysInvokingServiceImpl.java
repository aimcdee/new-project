package com.project.modules.sys.service.impl;

import com.project.modules.sys.dao.SysInvokingDao;
import com.project.modules.sys.service.SysInvokingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 中间调用Service
 *
 * @author liangyuding
 * @date 2020-03-20
 */
@Slf4j
@Service("sysInvokingService")
public class SysInvokingServiceImpl implements SysInvokingService {

    @Autowired
    private SysInvokingDao sysInvokingDao;


}
