package com.test;

import com.project.ProjectWechatApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liangyuding
 * @Date: 2020-04-15
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ProjectWechatApplication.class})// 指定启动类
public abstract class BaseUtilsTest {
}
