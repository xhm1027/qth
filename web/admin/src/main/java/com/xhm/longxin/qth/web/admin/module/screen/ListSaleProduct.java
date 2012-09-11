package com.xhm.longxin.qth.web.admin.module.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.util.StringUtil;
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
import com.xhm.longxin.qth.web.admin.vo.ProductCategoryVO;
import com.xhm.longxin.qth.web.admin.vo.SaleProductVO;

public class ListSaleProduct {
	@Autowired
	private SaleProductService saleProductService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductCategoryService productCategoryService;

	public void execute(@Param(name = "name") String name,
			@Param(name = "status") String status,
			@Param(name = "company") String company,
			@Param(name = "gmtPublishStart") String gmtPublishStart,
			@Param(name = "gmtPublishEnd") String gmtPublishEnd,
			@Param(name = "m") Long[] m,
			@Param(name = "nm") Long[] nm,
			@Param(name = "notFirst") String notFirst,
			@Param(name = "page") int page,
			@Param(name = "pageSize") int pageSize, Context context) {
		if (StringUtil.isEmpty(notFirst)) {
			List<ProductCategory> resourceCategoryList = productCategoryService
					.getAllResourceCategory();
			List<ProductCategory> materialCategoryList = productCategoryService
					.getAllMaterialCategory();
			// 如果第一次访问，类别全选
			m = new Long[materialCategoryList.size()];
			for (int i = 0; i < materialCategoryList.size(); i++) {
				m[i] = materialCategoryList.get(i).getId();
			}
			nm = new Long[resourceCategoryList.size()];
			for (int i = 0; i < resourceCategoryList.size(); i++) {
				nm[i] = resourceCategoryList.get(i).getId();
			}
		}
		// 分页参数处理
		SaleProductQuery productQuery = new SaleProductQuery();
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.defaultPageSize;
		}
		//查询参数
		productQuery.setCompany(company);
		productQuery.setName(name);
		productQuery.setStatus(status);
		// 采购类别
		List<Long> categoryIds = new ArrayList<Long>();
		List<Long> mIds = Arrays.asList(m);
		for (Long id : mIds) {
			categoryIds.add(id);
		}
		List<Long> notMCIds = Arrays.asList(nm);
		for (Long id : notMCIds) {
			categoryIds.add(id);
		}
		if (categoryIds.size() > 0) {
			productQuery.setCategoryIds(categoryIds);
		}
		// 发布日期
		productQuery.setGmtPublishStart(gmtPublishStart);
		productQuery.setGmtPublishEnd(gmtPublishEnd);

		// 往页面回写查询参数
		context.put("m", m);
		context.put("nm", nm);
		context.put("name", name);
		context.put("status", status);
		context.put("company", company);
		context.put("gmtPublishStart", gmtPublishStart);
		context.put("gmtPublishEnd", gmtPublishEnd);
		context.put("notFirst", "notFirst");//
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
						.asList(m), null));
		categoryQuery.setIsMaterial(IS.N);
		context.put("notMaterialCategories", wrapProductCategoryVO(
				productCategoryService.query(categoryQuery), null, Arrays
						.asList(nm)));
	}

	private List<SaleProductVO> wrapSaleProductVO(List<SaleProduct> productList) {
		List<SaleProductVO> resList = new ArrayList<SaleProductVO>();
		for (SaleProduct p : productList) {
			SaleProductVO proVo = new SaleProductVO();
			proVo.setId(p.getId());
			proVo.setImgs(p.getImgs());
			proVo.setDescription(p.getDescription());
			proVo.setLowestDealSize(p.getLowestDealSize());
			proVo.setName(p.getName());
			proVo.setPrice(p.getPrice());
			proVo.setUnit(p.getUnit());
			proVo.setQuantity(p.getQuantity());
			User user = userService.getUserByLoginId(p.getOwner());
			proVo.setCompany(user.getCompany());
			ProductCategory category=productCategoryService.getCategoryById(p.getCategoryId());
			proVo.setCategory(category.getName());
			proVo.setStatus(p.getStatus());
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
