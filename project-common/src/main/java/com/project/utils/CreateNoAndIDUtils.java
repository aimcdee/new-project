package com.project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 生成保证金单,退费单的ID及编号
 *
 * @author liangyuding
 * @date 2020-05-25
 */
@Component
public class CreateNoAndIDUtils {

    @Autowired
    private RedisUtils redisUtils;

    private static final String DW = "DW";
    private static final String DD = "DD";
    private static final String DR = "DR";
    private static final String DF = "DF";
    private static final String DEAL_WARES = "dealWares";
    private static final String DEAL_DEPOSIT = "dealDeposit";
    private static final String DEAL_REFUND = "dealRefund";
    private static final String DEAL_FINANCE = "dealFinance";

    /**生成商品单ID*/
    public String getDealWaresId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**生成保证金单ID*/
    public String getDealDepositId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**生成退费单ID*/
    public String getDealRefundId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**生成金融单ID*/
    public String getDealFinanceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**生成商品单编号*/
    public String getDealWaresCode() {
        //自增参数
        return DW + getDateFormate() + redisUtils.getTodayIncdecimal(DEAL_WARES);
    }

    /**生成保证金单编号*/
    public String getDealDepositCode() {
        //自增参数
        return DD + getDateFormate() + redisUtils.getTodayIncdecimal(DEAL_DEPOSIT);
    }

    /**生成退费单编号*/
    public String getDealRefundCode() {
        //自增参数
        return DR + getDateFormate() + redisUtils.getTodayIncdecimal(DEAL_REFUND);
    }

    /**生成金融单编号*/
    public String getDealFinanceCode() {
        //自增参数
        return DF + getDateFormate() + redisUtils.getTodayIncdecimal(DEAL_FINANCE);
    }

    /**将当前时间转为String格式*/
    private String getDateFormate() {
        //日期格式化 yyyyMMdd
        return DateUtils.format(new Date(System.currentTimeMillis()), DateUtils.DATE_SIMPLE_PATTERN);
    }
}
