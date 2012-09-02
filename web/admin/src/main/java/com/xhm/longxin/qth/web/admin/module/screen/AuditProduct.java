/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.screen;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.util.StringUtil;
import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.qth.dal.constant.AuditType;

/**
 * @author ren.zhangr
 *
 */
public class AuditProduct {
	@Autowired
	private BuyProductService buyProductService;
	@Autowired
	private SaleProductService saleProductService;

	public void execute(@Param(name = "id") Long id,
			@Param(name = "auditType") String auditType, Navigator nav,
			HttpSession session, Context context) {
		context.put("auditType", auditType);
		if(StringUtil.isEmpty(auditType)){
			return;
		}
		if(auditType.equalsIgnoreCase(AuditType.BUY_PRODUCT)){
			context.put("product", buyProductService.getBuyProductById(id));
		}
		if(auditType.equalsIgnoreCase(AuditType.SALE_PRODUCT)){
			context.put("product", saleProductService.getSaleProductById(id));
		}
	}

}
