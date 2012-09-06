package com.xhm.longxin.qth.web.user.module.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;
import com.xhm.longxin.qth.dal.query.CategoryQuery;
import com.xhm.longxin.qth.dal.query.QueryObject;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;
import com.xhm.longxin.qth.web.user.vo.ProductCategoryVO;
import com.xhm.longxin.qth.web.user.vo.SaleProductVO;

public class ListMySaleProduct {
	@Autowired
	private SaleProductService saleProductService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	HttpSession session;

	public void execute(@Param(name = "name") String name,
			@Param(name = "status") String status,
			@Param(name = "materialIds") Long[] materialIds,
			@Param(name = "notMaterialIds") Long[] notMaterialIds,
			@Param(name = "page") int page,
			@Param(name = "pageSize") int pageSize, Context context) {
		// 分页参数处理
		SaleProductQuery productQuery = new SaleProductQuery();
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.defaultPageSize;
		}
		productQuery.setName(name);
		// 用户冻结和审核失败时，需要扩展字段来进行描述标记，不能直接用BuyProduct
		productQuery.setStatus(status);
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);

		if (qthUser == null || qthUser.getId() == null) {
			return;
		}
		productQuery.setOwner(qthUser.getId());
		// 采购类别
		List<Long> categoryIds = new ArrayList<Long>();
		List<Long> mIds = Arrays.asList(materialIds);
		for (Long id : mIds) {
			categoryIds.add(id);
		}
		List<Long> notMCIds = Arrays.asList(notMaterialIds);
		for (Long id : notMCIds) {
			categoryIds.add(id);
		}
		if (categoryIds.size() > 0) {
			productQuery.setCategoryIds(categoryIds);
		}
		// 查询参数
		context.put("materialIds", materialIds);
		context.put("notMaterialIds", notMaterialIds);
		context.put("name", name);
		context.put("status", status);
		context.put("page", page);
		context.put("pageSize", pageSize);
		int totalCount = saleProductService.queryCount(productQuery);
		context.put("totalCount", totalCount);
		context.put("totalPage", (totalCount - 1) / pageSize + 1);
		List<SaleProduct> productList = saleProductService.query(productQuery,
				(page - 1) * pageSize, pageSize);
		context.put("productList", wrapSaleProductVO(productList));

		// 类别信息，作为查询条件用
		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setIsMaterial(IS.Y);
		context.put("materialCategories", wrapProductCategoryVO(
				productCategoryService.query(categoryQuery), Arrays
						.asList(materialIds), null));
		categoryQuery.setIsMaterial(IS.N);
		context.put("notMaterialCategories", wrapProductCategoryVO(
				productCategoryService.query(categoryQuery), null, Arrays
						.asList(notMaterialIds)));
	}

	private List<SaleProductVO> wrapSaleProductVO(List<SaleProduct> productList) {
		List<SaleProductVO> resList = new ArrayList<SaleProductVO>();
		for (SaleProduct p : productList) {
			SaleProductVO proVo = new SaleProductVO();
			proVo.setId(p.getId());
			proVo.setStatus(p.getStatus());
			proVo.setImgs(p.getImgs());
			proVo.setDescription(p.getDescription());
			proVo.setLowestDealSize(p.getLowestDealSize());
			proVo.setName(p.getName());
			proVo.setPrice(p.getPrice());
			proVo.setUnit(p.getUnit());
			proVo.setQuantity(p.getQuantity());
			ProductCategory category = productCategoryService.getCategoryById(p
					.getId());
			proVo.setCategoryName(category.getName());
			resList.add(proVo);
		}
		return resList;
	}

	/**
	 * @param query
	 * @param buyInterestIds
	 * @param saleInterestIds
	 * @param string
	 * @return
	 */
	private List<ProductCategoryVO> wrapProductCategoryVO(
			List<ProductCategory> categoryList, List<Long> saleInterestIds,
			List<Long> buyInterestIds) {
		List<ProductCategoryVO> productCategoryVOList = new ArrayList<ProductCategoryVO>();
		for (ProductCategory productCategory : categoryList) {
			ProductCategoryVO categoryVO = new ProductCategoryVO();
			categoryVO.setId(productCategory.getId());
			categoryVO.setName(productCategory.getName());
			if (saleInterestIds != null) {
				for (Long id : saleInterestIds) {
					if (productCategory.getId().equals(id)) {
						categoryVO.setChecked(true);
						break;
					}
				}
			}
			if (buyInterestIds != null) {
				for (Long id : buyInterestIds) {
					if (productCategory.getId().equals(id)) {
						categoryVO.setChecked(true);
						break;
					}
				}
			}
			productCategoryVOList.add(categoryVO);
		}
		return productCategoryVOList;
	}
}
