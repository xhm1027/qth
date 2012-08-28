package com.xhm.longxin.qth.web.user.module.screen;


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
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;

/**
 * 
 * @author xhm.xuhm
 *
 */
public class EditBuyProduct {
	@Autowired
	private UserService userService;
	@Autowired
	private BuyProductService buyProductService;

	@Autowired
	private ProductCategoryService productCategoryService;
	
	public void execute(@Param(name = "id") Long id,Navigator nav,HttpSession session, Context context) {
	
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);
		if(qthUser==null){
			nav.redirectTo(UserConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}
		User user = userService.getUserByLoginId(qthUser.getId());
		if(user==null){
			nav.redirectTo(UserConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}
		
		
		context.put("user", user);
		BuyProduct buyProduct = buyProductService.getBuyProductById(id);
		if(buyProduct!=null){
			ProductCategory category = productCategoryService.getCategoryById(buyProduct.getCategoryId());
			context.put("category", category);
		}
		context.put("buyProduct", buyProduct);
	}
}
