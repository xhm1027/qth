/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.action;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;

/**
 * @author ren.zhangr
 *
 */
public class SaleProductAction {
	@Autowired
	private SaleProductService saleProductService;

	public void doDeleteProduct(
			@Param("id") Long id) {
		saleProductService.deleteSaleProductById(id);
	}
}
