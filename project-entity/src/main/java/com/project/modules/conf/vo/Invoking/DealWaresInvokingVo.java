package com.project.modules.conf.vo.Invoking;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业商品管理InvokingVo
 *
 * @author liangyuding
 * @date 2020-06-08
 */
@Data
@Accessors(chain = true)
public class DealWaresInvokingVo implements Serializable {
    private static final long serialVersionUID = 5707797068807446377L;

    /**企业商品标题*/
    private String dealWaresTitle;
}
