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

package com.project.utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 日期处理
 *
 * @author liangyuding
 * @date 2020-03-10
 */
public class DateUtils {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_YEAR = "yyyy";
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyyMMdd)
     */
    public final static String DATE_SIMPLE_PATTERN = "yyyyMMdd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static int DAY_MS = 24 * 60 * 60 * 1000;

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 获取年
     *
     * @param date 日期
     * @return 返回yyyy格式年份
     */
    public static String getYear(Date date) {
        return format(date, DATE_YEAR);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String dateFormat(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyyMMdd
     *
     * @param date 日期
     * @return 返回yyyyMMdd格式日期
     */
    public static String dateTime(Date date) {
        return format(date, DATE_SIMPLE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     *
     * @param week 周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return 返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 获取date零点日期(如果date==null 获取当天的零点日期)
     *
     * @param date
     * @return date零点日期
     */
    public static Date getZero(Date date) {
        if (null == date) {
            date = new Date(System.currentTimeMillis());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //处理时分秒毫秒
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    /**
     * 获取月份，0表示1月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取date 23:59:59.999
     *
     * @param date
     * @return date 23:23:59.999
     */
    public static Date getLast(Date date) {
        if (null == date) {
            date = new Date(System.currentTimeMillis());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //处理时分秒毫秒
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date lastDate = calendar.getTime();
        return lastDate;
    }

    /**
     * first和second日期相差多少天
     *
     * @param first
     * @param second
     * @return 相差天数
     */
    public static int diffDay(Date first, Date second) {
        if (null != first && second != null) {
            return (int) ((second.getTime() - first.getTime()) / DAY_MS);
        }
        return 0;
    }

    /**
     * date 是星期几
     *
     * @param date 时间
     * @return 0.星期日 1.星期一 ... 6.星期六
     */
    public static Integer getWeekOfDate(Date date) {
        Integer weekIndex = 0;
        if (null != date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            weekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (weekIndex < 0) {
                weekIndex = 0;
            }
        }
        return weekIndex;

    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 现在到今天结束的毫秒数
     *
     * @return
     */
    public static Long getCurrent2TodayEndMillisTime() {
        Calendar todayEnd = Calendar.getInstance();
        // Calendar.HOUR 12小时制
        // HOUR_OF_DAY 24小时制
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTimeInMillis() - new Date().getTime();
    }

    /**
     * 获取date【月】第一天
     *
     * @param date
     * @return
     */
    public static Date getMonthStartDay(Date date) {
        if (null != date) {
            Date zero = getZero(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(zero);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 得到本周周一
     */
    public static Date getMondayOfThisWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return c.getTime();
    }

    /**
     * 获取当天的起始时间和结束时间
     *
     * @param day day不为空 获取day当天的;day=NULL时,获取昨天时间的起始时间和结束时间
     * @return 数组[0] = 起始时间 ; 数组[1]=结束时间
     */
    public static Date[] getToDays(Date day) {
        if (null == day) {
            // 昨天
            day = DateUtils.addDateDays(new Date(), -1);
        }
        final Date startDate = DateUtils.getZero(day);
        final Date endDate = DateUtils.getLast(day);
        Date ds[] = {startDate, endDate};
        return ds;
    }

    /**
     * 获取周的起始时间和结束时间
     *
     * @param day day不为空 获取day周内;day=NULL时,获取上周
     * @return 数组[0] = 周一 ; 数组[1]=周天
     */
    public static Date[] getToWeeks(Date day) {
        if (null == day) {
            // 上周
            day = DateUtils.addDateWeeks(new Date(), -1);
        }
        // 周一
        final Date startDate = DateUtils.getMondayOfThisWeek(DateUtils.getZero(day));
        // 周天
        final Date endDate = DateUtils.getLast(DateUtils.addDateDays(startDate, 6));
        Date ds[] = {startDate, endDate};
        return ds;
    }

    /**
     * 获取月的起始时间和结束时间
     *
     * @param day day不为空 获取day月内;day=NULL时,获取上月
     * @return 数组[0] = 本月1号 ; 数组[1]=本月最后一天
     */
    public static Date[] getToMonths(Date day) {
        if (null == day) {
            // 上月
            day = DateUtils.addDateMonths(new Date(), -1);
        }
        // 月的第一天
        final Date startDate = DateUtils.getMonthStartDay(DateUtils.getZero(day));
        // 月的最后一天
        final Date endDate = DateUtils.getLast(DateUtils.addDateDays(DateUtils.addDateMonths(startDate, 1), -1));
        Date ds[] = {startDate, endDate};
        return ds;
    }

    public static void main(String[] args) {
        Set s = new HashSet();
        s.add(null);
        System.out.println(s);
    }
}
