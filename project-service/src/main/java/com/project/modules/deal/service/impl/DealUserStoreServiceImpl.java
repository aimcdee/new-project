package com.project.modules.deal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.Constant;
import com.project.exception.RRException;
import com.project.modules.deal.dao.DealUserStoreDao;
import com.project.modules.deal.entity.DealUserStoreEntity;
import com.project.modules.deal.service.DealInvokingService;
import com.project.modules.deal.service.DealUserStoreService;
import com.project.modules.deal.vo.info.DealUserStoreInfoVo;
import com.project.modules.deal.vo.invoking.DealUserStoreInvokingVo;
import com.project.modules.deal.vo.list.DealStoreListVo;
import com.project.modules.deal.vo.list.DealUserStoreListVo;
import com.project.modules.deal.vo.save.DealUserStoreSaveVo;
import com.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户公司表Service
 *
 * @author liangyuding
 * @date 2020-03-37
 */

@Slf4j
@Service("dealUserStoreService")
public class DealUserStoreServiceImpl extends ServiceImpl<DealUserStoreDao, DealUserStoreEntity> implements DealUserStoreService {

    @Autowired
    private DealInvokingService dealInvokingService;
    @Autowired
    private TrimUtils trimUtils;
    @Autowired
    private CheckUtils checkUtils;

    /**
     * 客户申请成为企业用户
     * @param userStore
     */
    @Override
    @Transactional
    public void saveEntity(DealUserStoreSaveVo userStore) {
        try {
            trimUtils.beanValueTrim(userStore);
        } catch (Exception e) {
            e.printStackTrace();
        }
        save(getSaveDealUserStoreEntity(userStore));
    }

    /**
     * 分页查询企业申请验证
     * @param params
     * @return
     */
    @Override
    public PageUtils listPage(Map<String, Object> params) {
        Page<DealStoreListVo> page = new Query<DealStoreListVo>(params).getPage();
        log.info("审核状态:{}", MapUtils.getInteger(params, "examine"));
        List<DealStoreListVo> dealStoreListVos = baseMapper.listPage(
                page,
                MapUtils.getLong(params, "dealUserId"),
                MapUtils.getString(params, "dealUserPhone"),
                MapUtils.getInteger(params, "examine"),
                DateUtils.getDate(params, "startTime"),
                DateUtils.getDate(params, "endTime"));
        return new PageUtils(page.setRecords(dealStoreListVos));
    }

    /**
     * 分页查看客户申请成为企业的申请记录
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DealUserStoreListVo> page = new Query<DealUserStoreListVo>(params).getPage();
        List<DealUserStoreListVo> dealUserStoreListVos = baseMapper.list(page, MapUtils.getLong(params, "dealUserId"));
        return new PageUtils(page.setRecords(dealUserStoreListVos));
    }

    /**
     * 根据用户ID获取企业信息
     * @param dealUserId
     * @return
     */
    @Override
    public DealUserStoreInvokingVo getDealUserStoreInvokingVo(Long dealUserId) {
        return baseMapper.getDealUserStoreInvokingVo(dealUserId, Constant.Examine.SUCCESS.getExamine());
    }

    /**
     * 修改企业认证
     * @param dealUserId
     * @param sysUserId
     */
    @Override
    public void changeUserStore(Long dealUserId, Long sysUserId) {
        List<DealUserStoreEntity> dealUserStoreEntities = list(new QueryWrapper<DealUserStoreEntity>().eq("deal_user_id", dealUserId).between("examine", Constant.Examine.INREVIEW.getExamine(), Constant.Examine.SUCCESS.getExamine()));
        if (CollectionUtils.isNotEmpty(dealUserStoreEntities)){
            dealUserStoreEntities.forEach(dealUserStoreEntity -> {
                dealUserStoreEntity.setExamine(Constant.Examine.WASTE.getExamine()).setExamineUserId(sysUserId).setExamineTime(new Date());
                updateById(dealUserStoreEntity);
            });
        }
    }

    /**
     * 查看客户企业验证详情
     * @param storeId
     * @return
     */
    @Override
    public DealUserStoreInfoVo info(Long storeId) {
        return baseMapper.info(storeId);
    }

    /**
     * 审核客户企业验证
     * @param dealStoreId
     * @param examine
     * @param sysUserId
     * @param belongUserId
     */
    @Override
    @Transactional
    public void changeExamine(Long dealStoreId, Integer examine, Long sysUserId, Long belongUserId) {
        DealUserStoreEntity dealUserStoreEntity = getOne(new QueryWrapper<DealUserStoreEntity>().eq("deal_store_id", dealStoreId).last("LIMIT 1"));
        //如果企业验证审核为失败,当前单据的审核单据不为待审核时
        if (examine.equals(Constant.Examine.FAIL.getExamine()) && !dealUserStoreEntity.getExamine().equals(Constant.Examine.INREVIEW.getExamine())) {
            throw new RRException("操作失败,请确认审核状态是否为待审核");
        }
        dealUserStoreEntity.setExamine(examine).setExamineUserId(sysUserId).setExamineTime(new Date());
        //如果企业验证审核成功或者审核作废时
        if (examine.equals(Constant.Examine.SUCCESS.getExamine()) || examine.equals(Constant.Examine.WASTE.getExamine())) {
            if (examine.equals(Constant.Examine.SUCCESS.getExamine())) {
                //将用户除了该条记录以外的所有状态为成功的申请记录全都改为作废
                baseMapper.updateDealUserStoreExamine(dealUserStoreEntity.getDealUserId(), Constant.Examine.SUCCESS.getExamine(), dealUserStoreEntity.getDealStoreId(), Constant.Examine.WASTE.getExamine());
                dealUserStoreEntity
                        .setSysUserId(belongUserId)
                        .setSysUserName(dealInvokingService.getSysUserNameBySysUserId(belongUserId));
            }
            //修改客户类型
            changeUserType(dealUserStoreEntity);
        }
        updateById(dealUserStoreEntity);
    }

    //修改用户客户类型
    private void changeUserType(DealUserStoreEntity dealUserStoreEntity) {
        Integer type = dealUserStoreEntity.getExamine().equals(Constant.Examine.SUCCESS.getExamine()) ? Constant.StoreType.ENTERPRISE.getType() : Constant.StoreType.INDIVIDUAL.getType();
        //修改客户类型
        dealInvokingService.updateDealUserType(dealUserStoreEntity.getDealUserId(), type);
    }

    //设置DealUserStoreEntity保存对象
    private DealUserStoreEntity getSaveDealUserStoreEntity(DealUserStoreSaveVo userStore) {
        //校验客户状态是否为正常
        if (dealInvokingService.checkDealUserStatus(userStore.getDealUserId(), Constant.Status.NORMAL.getStatus()) <= 0){
            throw new RRException("客户已被禁用,请确认后再操作");
        }
        //校验客户是否存在还未审核的企业申请验证
        if (baseMapper.getCountExamineForInreview(userStore.getDealUserId(), Constant.Examine.INREVIEW.getExamine()) > 0){
            throw new RRException("已有提交申请验证还未审核,请勿重复提交");
        }
        DealUserStoreEntity dealUserStoreEntity = new DealUserStoreEntity();
        try {
            dealUserStoreEntity = (DealUserStoreEntity) JavaBeanUtils.mapToJavaBean(DealUserStoreEntity.class, JavaBeanUtils.javaBeanToMap(userStore));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dealUserStoreEntity
                .setDepositPrice(new BigDecimal(0))
                .setCreditGrade(Constant.CREDITGRADE)
                .setExamine(Constant.Examine.INREVIEW.getExamine());
        return dealUserStoreEntity;
    }

}
