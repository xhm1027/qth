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
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.qth.dal.constant.AuditType;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.web.admin.vo.BuyProductVO;
import com.xhm.longxin.qth.web.admin.vo.SaleProductVO;

/**
 * @author ren.zhangr
 *
 */
public class AuditProduct {
	@Autowired
	private BuyProductService buyProductService;
	@Autowired
	private SaleProductService saleProductService;
	@Autowired
	private ProductCategoryService productCategoryService;
	public void execute(@Param(name = "id") Long id,
			@Param(name = "auditType") String auditType, Navigator nav,
			HttpSession session, Context context) {
		context.put("auditType", auditType);
		if(StringUtil.isEmpty(auditType)){
			return;
		}
		if(auditType.equalsIgnoreCase(AuditType.BUY_PRODUCT)){
			BuyProduct product=buyProductService.getBuyProductById(id);
			if(product!=null){
				BuyProductVO proVO=new BuyProductVO();
				ProductCategory category=productCategoryService.getCategoryById(product.getCategoryId());
				proVO.setCategory(category.getName());
				proVO.setId(product.getId());
				proVO.setCategoryId(product.getCategoryId());
				//proVO.setDescription(product.getDescription());
				proVO.setImgs(product.getImgs());
				proVO.setLowestDealSize(product.getLowestDealSize());
				proVO.setName(product.getName());
				proVO.setOwner(product.getOwner());
				proVO.setPrice(product.getPrice());
				proVO.setUnit(product.getUnit());
				proVO.setQuantity(product.getQuantity());
				proVO.setStatus(product.getStatus());
				context.put("product", proVO);
			}

		}
		if(auditType.equalsIgnoreCase(AuditType.SALE_PRODUCT)){
			SaleProduct product=saleProductService.getSaleProductById(id);
			if(product!=null){
				SaleProductVO proVO=new SaleProductVO();
				ProductCategory category=productCategoryService.getCategoryById(product.getCategoryId());
				proVO.setCategory(category.getName());
				proVO.setId(product.getId());
				proVO.setCategoryId(product.getCategoryId());
				proVO.setDescription(product.getDescription());
				proVO.setImgs(product.getImgs());
				proVO.setUnit(product.getUnit());
				proVO.setLowestDealSize(product.getLowestDealSize());
				proVO.setName(product.getName());
				proVO.setOwner(product.getOwner());
				proVO.setPrice(product.getPrice());
				proVO.setQuantity(product.getQuantity());
				proVO.setStatus(product.getStatus());
				context.put("product", proVO);
			}
		}
	}

}
