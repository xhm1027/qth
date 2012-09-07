/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.CustomErrors;
import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.biz.user.vo.AuditProductVO;
import com.xhm.longxin.qth.dal.constant.AuditType;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.web.admin.common.AdminConstant;
import com.xhm.longxin.qth.web.admin.common.QthAdmin;

/**
 * @author ren.zhangr
 *
 */
public class SaleProductAction {
	@Autowired
	private SaleProductService saleProductService;

	public void doDeleteProduct(@Param("id") Long id, Context context) {
		try {
			SaleProduct product = saleProductService.getSaleProductById(id);
			if (product != null) {
				saleProductService.deleteSaleProductById(id);
				context.put("productDeleted", true);
				return;
			}
		} catch (Exception e) {
			context.put("productDeleted", false);
		}
		context.put("productDeleted", false);
	}

	public void doAuditProduct(
			@FormGroup("productAudit") AuditProductVO auditVO,
			@FormField(name = "auditProductInfo", group = "productAudit") CustomErrors info,
			@FormField(name = "auditProductErr", group = "productAudit") CustomErrors err,
			HttpSession session, Navigator nav, ParameterParser params,Context context) {
		QthAdmin qthAdmin = (QthAdmin) session
				.getAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);
		if (qthAdmin == null) {
			err.setMessage("auditFail");
			return;
		}
		// 如果产品状态不是待审核，则返回
		SaleProduct product = saleProductService.getSaleProductById(auditVO
				.getAuditId());
		if (product != null) {
			if (!ProductStatus.NEW.equals(product.getStatus())) {
				err.setMessage("auditStatusFail");
				return;
			}
		}
		auditVO.setAuditor(qthAdmin.getId());
		auditVO.setAuditType(AuditType.SALE_PRODUCT);
		boolean editResult = saleProductService.auditSaleProductById(auditVO);
		if (editResult) {
			info.setMessage("auditSuccess");
			context.put("productAudited", true);
		} else {
			err.setMessage("auditFail");
		}
	}
}
