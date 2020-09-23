package com.project.utils;

import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.deal.vo.save.DealAssessSaveVo;
import com.project.modules.sys.entity.SysUserEntity;
import com.project.modules.sys.vo.save.SysUserSaveVo;
import com.project.modules.sys.vo.update.SysUserUpdatePasswordVo;
import com.project.modules.sys.vo.update.SysUserUpdateVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.project.utils.ShiroUtils.*;

/**
 * 校验工具
 *
 * @author liangyuding
 * @date 2020-03-30
 */
@Component
public class CheckUtils {

    /**
     * 校验对象非空
     * @param object
     */
    public void checkEntityNotNull(Object object) {
        if (Objects.isNull(object)){
            throw new RRException("操作失败,请确认后再操作");
        }
    }

    /**
     * 校验集合对象非空
     * @param objects
     */
    public <T> void checkEntitiesNotNull(List<T> objects) {
        if (CollectionUtils.isEmpty(objects)){
            throw new RRException("操作失败,请刷新页面");
        }
    }

    /**
     * 校验对象属性非空
     * @param object
     */
    public <T> void checkNotNull(T object) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);//AccessibleTest类中的成员变量为private,故必须进行此操作
                if (field.get(object) == null && StringUtils.isBlank(field.get(object).toString())){
                    throw new RRException("操作失败,有必填字段不能为空");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册系统用户的非空校验
     * @param user
     */
    public void checkSaveUserNotNull(SysUserSaveVo user){
        if (StringUtils.isBlank(user.getUserName())){
            throw new RRException("请输入用户名");
        }
        if (StringUtils.isBlank(user.getPassword())){
            throw new RRException("请输入密码");
        }
        if (StringUtils.isBlank(user.getConfirm())){
            throw new RRException("请输入确认密码");
        }
        if (!user.getConfirm().equals(user.getPassword())){
            throw new RRException("两次密码输入不匹配,请重新输入");
        }
        if (StringUtils.isBlank(user.getPhone())){
            throw new RRException("请输入手机号码");
        }
        if (Objects.isNull(user.getDeptId()) || user.getDeptId() == 0){
            throw new RRException("请选择部门");
        }
        if (CollectionUtils.isEmpty(user.getRoleIdList())){
            throw new RRException("请选择角色");
        }
    }

    /**
     * 更新系统用户密码的非空校验
     * @param user
     */
    public void checkUpdatePasswordNotNull(SysUserUpdatePasswordVo user){
        if (StringUtils.isBlank(user.getOldPassword())){
            throw new RRException("原密码不能为空");
        }
        if (StringUtils.isBlank(user.getNewPassword())){
            throw new RRException("新密码不能为空");
        }
        if (StringUtils.isBlank(user.getConfirm())){
            throw new RRException("确认密码不能为空");
        }
        if (!user.getConfirm().equals(user.getNewPassword())){
            throw new RRException("新密码与确认不匹配,请重新输入");
        }
    }

    /**
     * 更新系统用户的非空校验
     * @param user
     */
    public void checkUpdateUserNotNull(SysUserUpdateVo user){
        if (StringUtils.isBlank(user.getUserName())){
            throw new RRException("用户名不能为空");
        }
        if (StringUtils.isBlank(user.getPhone())){
            throw new RRException("手机号码不能为空");
        }
        if (Objects.isNull(user.getDeptId()) || user.getDeptId() == 0){
            throw new RRException("请选择部门");
        }
        if (CollectionUtils.isEmpty(user.getRoleIdList())){
            throw new RRException("请选择角色");
        }
    }

    /**
     * 角色非空校验
     * @param roleName
     * @param deptIdList
     * @param menuIdList
     */
    public void checkRoleNotNull(String roleName, List<Long> deptIdList, List<Long> menuIdList) {
        if (StringUtils.isBlank(roleName)){
            throw new RRException("角色名称不能为空");
        }
        if (CollectionUtils.isEmpty(deptIdList)){
            throw new RRException("请选择部门");
        }
        if (CollectionUtils.isEmpty(menuIdList)){
            throw new RRException("请选择菜单权限");
        }
    }

    /**
     * 部门非空校验
     * @param deptName
     * @param parentId
     */
    public void checkDeptNotNull(String deptName, Long parentId) {
        if (StringUtils.isBlank(deptName)){
            throw new RRException("部门名称不能为空");
        }
        if (parentId == null || parentId == 0L){
            throw new RRException("请选择上级部门");
        }
    }

    /**
     * 校验登录用户信息
     * @param user
     * @param password
     */
    public void checkLoginUser(SysUserEntity user, String password) {
        //账号不存在
        if (user == null) {
            throw new RRException(StatusCode.LOGIN_USER_NOEXIST.getMsg());
        }
        //账号或密码错误
        if (!user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            throw new RRException(StatusCode.LOGIN_PASSWORD_ERROR.getMsg());
        }
        //账号锁定
        if (Constant.Status.DISABLE.getStatus().equals(user.getStatus())) {
            throw new RRException(StatusCode.LOGIN_USER_LOCK.getMsg());
        }
    }

    /**
     * 配置信息非空校验
     * @param name
     * @param code
     * @param value
     * @param memo
     */
    public void configNotNull(String name, String code, String value, String memo) {
        if (StringUtils.isBlank(name)){
            throw new RRException("配置名称不能为空");
        }
        if (StringUtils.isBlank(code)){
            throw new RRException("配置编码不能为空");
        }
        if (StringUtils.isBlank(value)){
            throw new RRException("配置值不能为空");
        }
        if (StringUtils.isBlank(memo)){
            throw new RRException("配置显示值不能为空");
        }
    }

    /**
     * 手机号码非空校验
     * @param phone
     */
    public void checkPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            throw new RRException("手机号码不能为空!");
        }
    }

    /**
     * 图片上传非空校验
     * @param picture
     */
    public void checkPrictureNotNull(MultipartFile picture){
        //检查文件是否为空
        if(picture.isEmpty()) {
            throw new RRException("请选择图片!");
        }
        //检查是否是图片
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(picture.getInputStream());
            if (Objects.isNull(bi)){
                throw new RRException("上传的文件不是图片");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验修改保证金单状态和当前操作的用户角色的权限
     * @param changeStatus
     * @param loginUserId
     * @param oldStatus
     * @param createUserId
     */
    public void checkRefundRole(Integer changeStatus, Long loginUserId, Integer oldStatus, Long createUserId) {
        switch (Constant.BillStatus.getStatusValues(changeStatus)){
            //如果需要修改单据状态为放弃
            case CANCEL:
                if (createUserId.equals(Constant.DEFAUL_SYSTEM_ID)){
                    break;
                }
                cancel(oldStatus, loginUserId, createUserId);
                break;
            //如果需要修改单据状态为驳回中
            case REJECT:
                reject(oldStatus);
                break;
            //如果需要修改单据状态为经理审核中
            case MANAGER:
                manager(oldStatus);
                break;
            //如果需要修改单据状态为通过
            case SUCCESS:
                success(oldStatus);
                break;
            default:
                break;
        }
    }

    /**
     * 校验修改状态和当前操作的用户角色的权限
     * @param changeStatus
     * @param loginUserId
     * @param oldStatus
     * @param createUserId
     */
    public void checkDepositRole(Integer changeStatus, Long loginUserId, Integer oldStatus, Long createUserId) {
        switch (Constant.BillStatus.getStatusValues(changeStatus)){
            //如果需要修改单据状态为放弃
            case CANCEL:
                cancel(oldStatus, loginUserId, createUserId);
                break;
            //如果需要修改单据状态为驳回中
            case REJECT:
                reject(oldStatus);
                break;
            //如果需要修改单据状态为经理审核中
            case MANAGER:
                manager(oldStatus);
                break;
            //如果需要修改单据状态为通过
            case SUCCESS:
                success(oldStatus);
                break;
            default:
                break;
        }
    }

    /**
     * 校验当前商品出售单据与修改的状态
     * @param changeStatus
     * @param oldStatus
     */
    public void checkAssessStatus(Integer changeStatus, Integer oldStatus) {
        switch (Constant.DropInStatus.getStatusValues(changeStatus)){
            case CANCEL:
                sellCancel(oldStatus);
                break;
            case PROCESSING:
                sellProcessing(oldStatus);
                break;
            case SUCCESS:
                sellSuccess(oldStatus);
                break;
            default:
                break;
        }
    }

    //修改的状态为已交易
    private void sellSuccess(Integer oldStatus) {
        if (!oldStatus.equals(Constant.DropInStatus.PROCESSING.getStatus())) {
            throw new RRException("操作失败,请刷新页面后确认当前单据状态");
        }
    }

    //修改的状态为上门中
    private void sellProcessing(Integer oldStatus) {
        if (!oldStatus.equals(Constant.DropInStatus.INREVIEW.getStatus())) {
            throw new RRException("操作失败,请刷新页面后确认当前单据状态");
        }
    }

    //修改的状态为已取消
    private void sellCancel(Integer oldStatus) {
        if (oldStatus.equals(Constant.DropInStatus.SUCCESS.getStatus())) {
            throw new RRException("操作失败,请刷新页面后确认当前单据状态");
        }
    }

    //如果需要修改单据状态为放弃
    private void cancel(Integer oldStatus, Long loginUserId, Long createUserId) {
        //当前单据状态不是已驳回的状态
        if (!oldStatus.equals(Constant.BillStatus.REJECT.getStatus())){
            throw new RRException("操作失败,请确认单据当前状态");
        }
        //登录用户如果不是超级管理员或者普通管理员或者总经理或者财务经理或者这张单据不属于该用户
        if (!(loginUserId.equals(createUserId) || isSuperAdmin() || isAdmin() || isManager() || isFinanceManager())){
            throw new RRException("操作失败,该单据不属于你");
        }
    }

    //如果需要修改单据状态为驳回
    private void reject(Integer oldStatus) {
        //当前单据状态不是财务审核中或者经理审核中时
        if (!(oldStatus.equals(Constant.BillStatus.FINANCE.getStatus()) || oldStatus.equals(Constant.BillStatus.MANAGER.getStatus()))){
            throw new RRException("操作失败,请确认单据当前状态");
        }
        //登录用户如果不是超级管理员或者普通管理员或者总经理或者会计专员或者财务经理
        if (!(isSuperAdmin() || isAdmin() || isManager() || isFinance() || isFinanceManager())){
            throw new RRException("操作失败,该单据不属于你");
        }
    }

    //如果需要修改单据状态为经理审核中
    private void manager(Integer oldStatus) {
        //当前单据状态不是财务审核中的状态
        if (!oldStatus.equals(Constant.BillStatus.FINANCE.getStatus())){
            throw new RRException("操作失败,请确认单据当前状态");
        }
        //登录用户如果不是超级管理员或者普通管理员或者总经理或者会计专员或者财务经理
        if (!(isSuperAdmin() || isAdmin() || isManager() || isFinance() || isFinanceManager())){
            throw new RRException("操作失败,你没有权限对此单据进行修改");
        }
    }

    //如果需要修改单据状态为通过
    private void success(Integer oldStatus) {
        //当前单据状态不是经理审核中的状态
        if (!oldStatus.equals(Constant.BillStatus.MANAGER.getStatus())){
            throw new RRException("操作失败,请确认单据当前状态");
        }
        //登录用户如果不是超级管理员或者普通管理员或者总经理
        if (!(isSuperAdmin() || isAdmin() || isManager())){
            throw new RRException("操作失败,你没有权限对此单据进行修改");
        }
    }

    //校验金额
    public void checkPrice(BigDecimal depositPrice) {
        if (depositPrice.compareTo(new BigDecimal(0)) == -1 || depositPrice.compareTo(new BigDecimal(0)) == 0){
            throw new RRException("操作失败,请输入正确金额");
        }
    }

    /**
     * 校验修改状态和当前操作的用户角色的权限
     * @param status
     * @param onlineStatus
     * @param sellStatus
     */
    public void checkWareRole(Integer status, Integer onlineStatus, Integer sellStatus) {
        //如果当前企业商品出售状态为已出售并且需要修改的状态不为下架或者商品当前状态为已下架
        if ((sellStatus.equals(Constant.WaresSellStatus.SALE.getStatus()) && !status.equals(Constant.WaresOnLineStatus.UNLINE.getStatus())) || onlineStatus.equals(Constant.WaresOnLineStatus.UNLINE.getStatus())){
            throw new RRException("操作失败,该商品已出售或已下架");
        }
        switch (Constant.WaresOnLineStatus.getStatusValues(status)){
            //如果需要修改状态为驳回
            case REJECT:
                waresReject(onlineStatus);
                break;
            //如果需要修改状态为经理审核中
            case MANAGER:
                waresManager(onlineStatus);
                break;
            //如果需要修改状态为上线
            case ONLINE:
                waresOnline(onlineStatus);
                break;
            //如果需要修改状态为下架
            case UNLINE:
                waresUnline(onlineStatus);
                break;
            default:
                break;
        }
    }
    //如果需要修改状态为下架
    private void waresUnline(Integer onlineStatus) {
        //当前状态不是已驳回或者上架的状态
        if (!(onlineStatus.equals(Constant.WaresOnLineStatus.ONLINE.getStatus())) || onlineStatus.equals(Constant.WaresOnLineStatus.REJECT.getStatus())){
            throw new RRException("操作失败,请确认单据当前状态");
        }
        //登录用户如果不是超级管理员或者普通管理员或者总经理
        if (!(isSuperAdmin() || isAdmin() || isManager())){
            throw new RRException("操作失败,你没有权限对此单据进行修改");
        }
    }

    //如果需要修改状态为上线
    private void waresOnline(Integer onlineStatus) {
        //当前状态不是经理审核中的状态
        if (!onlineStatus.equals(Constant.WaresOnLineStatus.MANAGER.getStatus())){
            throw new RRException("操作失败,请确认单据当前状态");
        }
        //登录用户如果不是超级管理员或者普通管理员或者总经理
        if (!(isSuperAdmin() || isAdmin() || isManager() || isSaleManager())){
            throw new RRException("操作失败,你没有权限对此单据进行修改");
        }
    }

    //如果需要修改状态为经理审核中
    private void waresManager(Integer onlineStatus) {
        //当前状态不是销售审核中的状态
        if (!onlineStatus.equals(Constant.WaresOnLineStatus.SALE.getStatus())){
            throw new RRException("操作失败,请确认单据当前状态");
        }
        //登录用户如果不是超级管理员或者普通管理员或者会计专员或者财务经理
        if (!(isSuperAdmin() || isAdmin() || isManager() || isSale() || isSaleManager())){
            throw new RRException("操作失败,你没有权限对此单据进行修改");
        }
    }

    //修改状态为驳回
    private void waresReject(Integer onlineStatus) {
        //当前企业商品的上线状态不是销售审核中或者经理审核中时
        if (!(onlineStatus.equals(Constant.WaresOnLineStatus.SALE.getStatus()) || onlineStatus.equals(Constant.WaresOnLineStatus.MANAGER.getStatus()))){
            throw new RRException("操作失败,请确认商品当前状态");
        }
        //登录用户如果不是超级管理员或者普通管理员或者总经理或者销售专员或者销售经理
        if (!(isSuperAdmin() || isAdmin() || isManager() || isSale() || isSaleManager())){
            throw new RRException("操作失败,你没有权限操作该单据");
        }
    }

    /**
     * 检验修改的状态与金融单据当前的状态
     * @param oldStatus
     * @param changeStatus
     */
    public void checkFinanceStatus(Integer oldStatus, Integer changeStatus) {
        switch (Constant.FinanceStatus.getStatusValues(changeStatus)){
            //修改状态为作废
            case WASTE:
                financeWaste(oldStatus);
                break;
            //修改状态在处理中
            case CHECKPENDING:
                financeCheckpending(oldStatus);
                break;
            //修改状态为已处理
            case SUCCESS:
                financSuccess(oldStatus);
                break;
            default:
                break;
        }
    }

    //修改状态为已处理
    private void financSuccess(Integer oldStatus) {
        if (!oldStatus.equals(Constant.FinanceStatus.CHECKPENDING.getStatus())){
            throw new RRException("操作失败,请确认金融单当前状态");
        }
    }

    //修改状态在处理中
    private void financeCheckpending(Integer oldStatus) {
        if (!oldStatus.equals(Constant.FinanceStatus.INREVIEW.getStatus())){
            throw new RRException("操作失败,请确认金融单当前状态");
        }
    }

    //修改状态为作废
    private void financeWaste(Integer oldStatus) {
        if (oldStatus.equals(Constant.FinanceStatus.SUCCESS.getStatus())){
            throw new RRException("操作失败,请确认金融单当前状态");
        }
    }

    /**
     * 校验咨询分期跟进状态与修改状态
     * @param oldStatus
     * @param changeStatus
     */
    public void checkInstallmentStatus(Integer oldStatus, Integer changeStatus) {
        switch (Constant.InstallmentStatus.getStatusValues(changeStatus)){
            //修改状态为作废
            case WASTE:
                changeInstallmentStatus(oldStatus);
                break;
            //修改状态为已处理
            case SUCCESS:
                changeInstallmentStatus(oldStatus);
                break;
            default:
                break;
        }
    }

    //修改的状态为作废或者已处理
    private void changeInstallmentStatus(Integer oldStatus) {
        if (!oldStatus.equals(Constant.InstallmentStatus.CHECKPENDING.getStatus())){
            throw new RRException("操作失败,请确认金融单当前状态");
        }
    }

    /**
     * 校验新增对象属性非空
     * @param assess
     */
    public void checkAsswssSaveNotNull(DealAssessSaveVo assess) {
        if (Objects.isNull(assess.getCouBrandId()) || assess.getCouBrandId() == 0){
            throw new RRException("所属品牌不能为空");
        }
        if (Objects.isNull(assess.getCouSeriesId()) || assess.getCouSeriesId() == 0){
            throw new RRException("所属系列不能为空");
        }
        if (Objects.isNull(assess.getCouWaresId()) || assess.getCouWaresId() == 0){
            throw new RRException("所属商品不能为空");
        }
        if (Objects.isNull(assess.getRegisterTime())){
            throw new RRException("上牌时间不能为空");
        }
        if (Objects.isNull(assess.getProAreaId()) || assess.getProAreaId() == 0){
            throw new RRException("请选择所在省份");
        }
        if (Objects.isNull(assess.getDistance())){
            throw new RRException("请填写行驶里程");
        }
        if (Objects.isNull(assess.getDriveImage())){
            throw new RRException("请上传行驶证图片");
        }
        if (CollectionUtils.isEmpty(assess.getWaresImages())){
            throw new RRException("请上传商品图片");
        }
    }
}
