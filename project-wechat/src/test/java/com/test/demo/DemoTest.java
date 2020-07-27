package com.test.demo;

import com.project.utils.DateUtils;
import com.project.utils.RedisUtils;
import com.test.BaseUtilsTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Slf4j
public class DemoTest extends BaseUtilsTest {

    @Autowired
    private RedisUtils redisUtils;
    private DateUtils dateUtils;

    @Test
    public void updateStuRegPrice(){
        Date time = new Date();
        System.out.println("时间:" + time);
        String dateTime = dateUtils.getYear(time);
        System.out.println("年款:" + dateTime);
    }

    public static void main(String[] args) {


    }

}
