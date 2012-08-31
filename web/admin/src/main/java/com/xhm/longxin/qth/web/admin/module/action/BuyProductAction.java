/**
 *
 */
package com.xhm.longxin.qth.web.admin.module.action;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.BuyProductService;

/**
 * @author ren.zhangr
 *
 */
public class BuyProductAction {
	@Autowired
	private BuyProductService buyProductService;

	public void doDeleteProduct(
			@Param("id") Long id) {
		buyProductService.deleteBuyProductById(id);
	}
}
