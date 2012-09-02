/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.CustomErrors;
import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.biz.user.vo.AuditProductVO;
import com.xhm.longxin.biz.user.vo.UserAuditVO;
import com.xhm.longxin.qth.dal.constant.AuditType;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.web.admin.common.AdminConstant;
import com.xhm.longxin.qth.web.admin.common.QthAdmin;

/**
 * @author ren.zhangr
 *
 */
public class BuyProductAction {
	@Autowired
	private BuyProductService buyProductService;

	public void doDeleteProduct(@Param("id") Long id) {
		buyProductService.deleteBuyProductById(id);
	}

	public void doAuditProduct(
			@FormGroup("productAudit") AuditProductVO auditVO,
			@FormField(name = "auditProductInfo", group = "productAudit") CustomErrors info,
			@FormField(name = "auditProductErr", group = "productAudit") CustomErrors err,
			HttpSession session, Navigator nav, ParameterParser params) {
		QthAdmin qthAdmin = (QthAdmin) session
				.getAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);
		if (qthAdmin == null) {
			err.setMessage("auditFail");
			return;
		}
		//如果产品状态不是待审核，则返回
		BuyProduct product=buyProductService.getBuyProductById(auditVO.getAuditId());
		if(product!=null){
			if(!ProductStatus.NEW.equals(product.getStatus())){
				err.setMessage("auditStatusFail");
				return;
			}
		}
		auditVO.setAuditor(qthAdmin.getId());
		auditVO.setAuditType(AuditType.BUY_PRODUCT);
		boolean editResult = buyProductService.auditBuyProductById(auditVO);
		if (editResult) {
			info.setMessage("auditSuccess");
		} else {
			err.setMessage("auditFail");
		}
	}
}
