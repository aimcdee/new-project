package com.project.constant;

import java.math.BigDecimal;

/**
 * 常量
 *
 * @author liangyuding
 * @date 2020-03-18
 */
public class Constant {

    /**超级管理员ID*/
    public static final Long SUPER_ADMIN = 1L;

    /**超级管理员ID*/
    public static final Long SUPER_ADMIN_STRING = 2L;

    /**总部*/
    public static final Long HEADQUARTERS = 1L;

    /**默认菜单*/
    public static final Long DEFAUL_TROLE = -666666L;

    /**默认部门*/
    public static final Long DEFAUL_TDEPT = -666666L;

    /**内部调用评估接口*/
    public static final String DEAL_PASSWORD = "123456";

    /**默认ID*/
    public static final Long DEFAUL_ID = 0L;

    /**默认名称*/
    public static final String DEFAUL_NAME = "无";

    /**默认系统ID*/
    public static final Long DEFAUL_SYSTEM_ID = 0L;

    /**默认系统名称*/
    public static final String DEFAUL_SYSTEM_NAME = "系统";

    /**默认系统名称*/
    public static final String DEFAUL_CASH_OUT = "客户提现";

    /**默认系统名称*/
    public static final String DEFAUL_REMARK = "金融单";

    /**非企业客户*/
    public static final String DEFAUL_INDIVIDUAL = "您不是企业客户无法操作该功能";

    /**默认广东省级ID*/
    public static final BigDecimal DEFAUL_PRICE = new BigDecimal(150);

    /**初始化信誉等级*/
    public static final Integer CREDITGRADE = 0;

    /**默认赠送积分*/
    public static final Long INTEGRAL = 100L;

    /**内部调用轮播图接口*/
    public static final String CONF_BANNER_PATH = "/wx/conf/banner";

    /**内部调用区域接口*/
    public static final String CUST_AREA_PATH = "/wx/cust/area";

    /**内部调用系统商品接口*/
    public static final String COU_WARES_PATH = "/wx/cou/wares";

    /**内部调用系统商品品牌接口*/
    public static final String COU_BRAND_PATH = "/wx/cou/wares/brand";

    /**内部调用系统品牌系列接口*/
    public static final String COU_SERIES_PATH = "/wx/cou/wares/series";

    /**内部调用系统商品类型接口*/
    public static final String COU_MODEL_PATH = "/wx/cou/wares/model";

    /**内部调用客户接口*/
    public static final String DEAL_USER_PATH = "/wx/deal/user";

    /**内部调用客户企业接口*/
    public static final String DEAL_USER_STORE_PATH = "/wx/deal/user/store";

    /**内部调用客户企业退费单接口*/
    public static final String DEAL_USER_STORE_REFUND_PATH = "/wx/deal/user/store/refund";

    /**内部调用客户企业金融单接口*/
    public static final String DEAL_USER_STORE_FINANCE_PATH = "/wx/deal/user/store/finance";

    /**内部调用评估接口*/
    public static final String DEAL_ASSESS_PATH = "/wx/deal/assess";

    /**内部调用评估商品出售接口*/
    public static final String DEAL_ASSESS_SELL_PATH = "/wx/deal/assess/sell";

    /**内部调用出售商品接口*/
    public static final String DEAL_WARES_PATH = "/wx/deal/wares";

    /**内部调用出售咨询商品分期接口*/
    public static final String DEAL_WARES_INSTALLMENT_PATH = "/xw/deal/wares/installment";

    /**客户上传图片路径*/
    public static final String DEAL_LINUX_IMAGE_PATH = "/usr/local/Image/dealFile/";

    /**系统品牌模块上传图片路径*/
    public static final String COU_LINUX_IMAGE_PATH = "/usr/local/Image/couFile/";
    public static final String JJWT_SECRETKEY = "zj.car@2020";
    public static final String JJWT_KEY_USERID = "userId";
    public static final String JJWT_KEY_NAME = "name";
    public static final String JJWT_KEY_PHONE = "phone";


    //失效时间 2小时 秒
    public static final int LOGIN_EXPIRES = 7200;

    /**
     * 菜单类型
     *   0.目录  1.菜单  2.按钮
     *
     * @author liangyuding
     * @date 2020-03-10
     */
    public enum MenuType {
        CATALOG(0),MENU(1),BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Role{

        SUPER_ADMIN("SUPER_ADMIN", "超级管理员"),
        SUPER_ADMIN_STRING("SUPER_ADMIN_STRING", "超级管理员"),
        ADMIN("ADMIN", "管理员"),
        MANAGER("MANAGER", "总经理"),
        FINANCE_MANAGER("FINANCE_MANAGER", "财务经理"),
        FINANCE("FINANCE", "会计专员"),
        SALE_MANAGER("SALE_MANAGER", "销售经理"),
        SALE("SALE", "销售专员");

        private String code;
        private String roleName;

        Role(String code, String roleName) {
            this.code = code;
            this.roleName = roleName;
        }

        public String getCode() {
            return code;
        }

        public void setCode() {
            this.code = code;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }

    public enum RoleId{
        SALE_MANAGER(5L, "销售经理"), SALE(11L, "销售专员");
        private Long roleId;
        private String roleName;
        RoleId (Long roleId, String roleName){
            this.roleId = roleId;
            this.roleName = roleName;
        }

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }

    /**
     * 通用状态
     *   0.禁用   1.正常
     */
    public enum Status {
        DISABLE(0), NORMAL(1);

        private Integer status;

        Status(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public boolean equals(Integer status) {
            return status.equals(this.status);
        }
    }

    /**
     * 通用审核状态 0.失败 1.审核中 2.通过 3.作废
     */
    public enum Examine {
        FAIL(0), INREVIEW(1), SUCCESS(2), WASTE(3);

        private Integer examine;

        Examine(Integer examine) {
            this.examine = examine;
        }

        public Integer getExamine() {
            return examine;
        }

        public void setExamine(Integer examine) {
            this.examine = examine;
        }
    }

    /**
     * 区域类型 0.省 1.市 2.县/区
     */
    public enum AreaType {
        PROVINCE(0), CITY(1), COUNTY(2);

        private Integer type;

        AreaType(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public static AreaType getTypeValues(Integer type){
            if (!type.equals(null)){
                for (AreaType typeEnum : values()){
                    if (typeEnum.getType().equals(type)){
                        return typeEnum;
                    }
                }
            }
            return null;
        }
    }

    /**
     * 客户类型
     * 0.个人 1.车商
     */
    public enum StoreType {
        INDIVIDUAL(0), ENTERPRISE(1);

        private Integer type;

        StoreType(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public boolean equals(Integer type) {
            return type.equals(this.type);
        }
    }

    /**
     * 单据类型
     *   0.保证金单 1.退费单 2.金融单
     */
    public enum BillType {
        DEPOSIT(0), REFUND(1), FINANCE(2);

        private Integer type;

        BillType(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer code) {
            this.type = type;
        }
    }

    /**
     * 退费类型
     *   0.扣费 1.提现
     */
    public enum RefundType {
        REFUND(0), CASHOUT(1);

        private Integer type;

        RefundType(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer code) {
            this.type = type;
        }
    }

    /**
     * 单据审核状态
     *   0.放弃 1.驳回 2.财务审核中 3.经理审核中 4.通过
     */
    public enum BillStatus {
        CANCEL(0), REJECT(1), FINANCE(2), MANAGER(3), SUCCESS(4);

        private Integer status;

        BillStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public static BillStatus getStatusValues(Integer status){
            if (!status.equals(null)){
                for (BillStatus statusEnum : values()){
                    if (statusEnum.getStatus().equals(status)){
                        return statusEnum;
                    }
                }
            }
            return null;
        }
    }

    /**
     * 金融单状态
     *   0.作废 1.待处理 2.处理中 3.已完成
     */
    public enum FinanceStatus {
        WASTE(0), INREVIEW(1), CHECKPENDING(2), SUCCESS(3);

        private Integer status;

        FinanceStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public static FinanceStatus getStatusValues(Integer status){
            if (!status.equals(null)){
                for (FinanceStatus statusEnum: values()) {
                    if (statusEnum.getStatus().equals(status)){
                        return statusEnum;
                    }
                }
            }
            return null;
        }
    }

    /**
     * 个人商品审核状态
     *   0.待审核   1.已审核   2.已作废
     */
    public enum AssessStatus {
        CHECKPENDING(0), PENDING(1), WASTE(2);

        private Integer status;

        AssessStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public boolean equals(Integer status) {
            return status.equals(this.status);
        }
    }

    /**
     * 个人商品交易情况
     *   0.未交易   1.交易中   2.已交易
     */
    public enum AssessSellStatus {
        INREVIEW(0), PROCESSING(1), SUCCESS(2);

        private Integer status;

        AssessSellStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

    }

    /**
     * 个人商品上门情况
     *   0.已取消   1.待上门   2.上门中   3.已交易
     */
    public enum DropInStatus {
        CANCEL(0), INREVIEW(1), PROCESSING(2), SUCCESS(3);

        private Integer status;

        DropInStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public static DropInStatus getStatusValues(Integer status){
            if (!status.equals(null)){
                for (DropInStatus statusEnum : values()){
                    if (statusEnum.getStatus().equals(status)){
                        return statusEnum;
                    }
                }
            }
            return null;
        }
    }

    /**
     * 出售商品图片类型
     *   0.商品图   1.行驶证图
     */
    public enum ImageType {
        WARES(0), DRIVE(1);

        private Integer type;

        ImageType(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }

    /**
     * 是否商品封面图
     *   0.不   1.是
     */
    public enum IsWaresCover {
        NO(0), YES(1);

        private Integer type;

        IsWaresCover(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }

    /**
     * 企业商品上架情况
     *   0.驳回 1.销售审核中 2.经理审核中 4.上架 4.下架
     */
    public enum WaresOnLineStatus {
        REJECT(0), SALE(1), MANAGER(2), ONLINE(3), UNLINE(4);

        private Integer status;

        WaresOnLineStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public static WaresOnLineStatus getStatusValues(Integer status){
            if (!status.equals(null)){
                for (WaresOnLineStatus statusEnum : values()){
                    if (statusEnum.getStatus().equals(status)){
                        return statusEnum;
                    }
                }
            }
            return null;
        }
    }

    /**
     * 企业商品出售状态
     *   0.未出售   1.已出售
     */
    public enum WaresSellStatus {
        UNSALE(0), SALE(1);

        private Integer status;

        WaresSellStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

    /**
     * 分期咨询状态
     *   0.已取消 2.待处理 3.已完成
     */
    public enum InstallmentStatus {
        WASTE(0), CHECKPENDING(1), SUCCESS(2);

        private Integer status;

        InstallmentStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public static InstallmentStatus getStatusValues(Integer status){
            if (!status.equals(null)){
                for (InstallmentStatus statusEnum: values()) {
                    if (statusEnum.getStatus().equals(status)){
                        return statusEnum;
                    }
                }
            }
            return null;
        }
    }

    /**
     * 图片文件夹后缀路径或上传的图片类型
     * 0.企业门面 1.商品 2.评估 3.商品证件 4.轮播 5.商品品牌 6.商品型号
     */
    public enum UploadImage {

        WARES(0, "/wares/"),
        DRIVINGID(1, "/drive/"),
        STORE(2, "/store/"),
        ASSESS(3, "/assess/"),
        DEAL(4, "/deal/"),
        BANNER(6, "/banner/"),
        WARESBRAND(6, "/wares/brand/"),
        WARESMODEL(7, "/wares/model/");


        private Integer type;
        private String text;

        UploadImage(Integer type, String text) {
            this.type = type;
            this.text = text;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    /**
     * 微信小程序appId和appSecret
     */
    public enum WeChatKeyEnum {
        WECHATKEY("wx0a09f6863407a03b", "b04e185ef5ebec1fc28509a246972ce0");

        WeChatKeyEnum(String appId, String appSecret) {
            this.appId = appId;
            this.appSecret = appSecret;
        }

        private String appId;
        private String appSecret;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppSecret() {
            return appSecret;
        }

        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }
    }
}
