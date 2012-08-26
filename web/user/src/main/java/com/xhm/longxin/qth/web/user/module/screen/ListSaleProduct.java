package com.xhm.longxin.qth.web.user.module.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.query.CategoryQuery;
import com.xhm.longxin.qth.dal.query.QueryObject;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;
import com.xhm.longxin.qth.web.user.vo.ProductCategoryVO;

public class ListSaleProduct {
	@Autowired
	private SaleProductService saleProductService;
	@Autowired
	private ProductCategoryService productCategoryService;

	public void execute(@Param(name = "name") String name,
			@Param(name = "materialIds") Long[] materialIds,
			@Param(name = "notMaterialIds") Long[] notMaterialIds,
			@Param(name = "order") String order,
			@Param(name = "orderDesc") String orderDesc,
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
		// 采购类别
		List<Long> categoryIds = Arrays.asList(materialIds);
		categoryIds.addAll(Arrays.asList(notMaterialIds));
		if (categoryIds.size() > 0) {
			productQuery.setCategoryIds(categoryIds);
		}
		// 排序
		if (order != null && order.equals("price")) {
			productQuery.setOrderByPrice(true);
		}
		if (order != null && order.equals("publish")) {
			productQuery.setOrderModified(true);
		}
		if (orderDesc != null) {
			productQuery.setOrderDesc(true);
		}
		// 查询参数
		context.put("name", name);
		context.put("order", order);
		context.put("page", page);
		context.put("pageSize", pageSize);
		context.put("orderDesc", orderDesc);
		int totalCount =  saleProductService.queryCount(productQuery);
		context.put("totalCount", totalCount);
		context.put("totalPage", (totalCount - 1) / pageSize + 1);
		context.put("productList", saleProductService.query(productQuery,
				(page - 1) * pageSize, pageSize));

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
