package com.xhm.longxin.qth.web.admin.module.screen;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;

/**
 * 
 * @author xhm.xuhm
 *
 */
public class viewSaleProduct {
	@Autowired
	private SaleProductService saleProductService;

	@Autowired
	private ProductCategoryService productCategoryService;
	
	public void execute(@Param(name = "id") Long id,Navigator nav,HttpSession session, Context context) {
	
		SaleProduct saleProduct = saleProductService.getSaleProductById(id);
		if(saleProduct!=null){
			ProductCategory category = productCategoryService.getCategoryById(saleProduct.getCategoryId());
			context.put("category", category);
		}
		context.put("saleProduct", saleProduct);
	}
}
