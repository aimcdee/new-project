package com.project.utils;

import com.project.exception.RRException;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 设置时间格式工具类
 *
 * @author liangyuding
 * @create 2020-06-06
 */

@Component
public class DDateUtils {

    /**
     * 设置时间格式
     *
     * @param params
     * @param dateKey
     * @return
     */
    public Date getDate(Map<String, Object> params, String dateKey) {
        Date startDateTime;
        try {
            String startDateTimeString = MapUtils.getString(params, dateKey);
            startDateTime = DateUtils.stringToDate(startDateTimeString, DateUtils.DATE_TIME_PATTERN);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("日期格式必须是:" + DateUtils.DATE_TIME_PATTERN);
        }
        return startDateTime;
    }
}
