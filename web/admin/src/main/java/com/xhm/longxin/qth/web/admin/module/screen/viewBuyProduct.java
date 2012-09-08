package com.xhm.longxin.qth.web.admin.module.screen;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.User;

/**
 *
 * @author xhm.xuhm
 *
 */
public class viewBuyProduct {
	@Autowired
	private BuyProductService buyProductService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductCategoryService productCategoryService;

	public void execute(@Param(name = "id") Long id,Navigator nav,HttpSession session, Context context) {

		BuyProduct buyProduct = buyProductService.getBuyProductById(id);
		if(buyProduct!=null){
			ProductCategory category = productCategoryService.getCategoryById(buyProduct.getCategoryId());
			context.put("category", category);
			User user = userService.getUserByLoginId(buyProduct.getOwner());
			context.put("company", user);
		}
		context.put("buyProduct", buyProduct);
	}
}
