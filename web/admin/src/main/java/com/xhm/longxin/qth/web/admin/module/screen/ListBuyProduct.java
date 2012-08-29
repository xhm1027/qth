package com.xhm.longxin.qth.web.admin.module.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.query.CategoryQuery;
import com.xhm.longxin.qth.dal.query.QueryObject;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;
import com.xhm.longxin.qth.web.admin.vo.BuyProductVO;
import com.xhm.longxin.qth.web.admin.vo.ProductCategoryVO;

public class ListBuyProduct {
	@Autowired
	private BuyProductService buyProductService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductCategoryService productCategoryService;

	public void execute(@Param(name = "name") String name,
			@Param(name = "status") String status,
			@Param(name = "company") String company,
			@Param(name = "gmtPublishStart") String gmtPublishStart,
			@Param(name = "gmtPublishEnd") String gmtPublishEnd,
			@Param(name = "materialIds") Long[] materialIds,
			@Param(name = "notMaterialIds") Long[] notMaterialIds,
			@Param(name = "page") int page,
			@Param(name = "pageSize") int pageSize, Context context) {
		// ��ҳ��������
		BuyProductQuery productQuery = new BuyProductQuery();
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.defaultPageSize;
		}
		//��ѯ����
		productQuery.setCompany(company);
		productQuery.setName(name);
		productQuery.setStatus(status);
		// �ɹ����
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
		// ��������
		productQuery.setGmtPublishStart(gmtPublishStart);
		productQuery.setGmtPublishEnd(gmtPublishEnd);

		// ��ҳ���д��ѯ����
		context.put("materialIds", materialIds);
		context.put("notMaterialIds", notMaterialIds);
		context.put("name", name);
		context.put("status", status);
		context.put("company", company);
		context.put("gmtPublishStart", gmtPublishStart);
		context.put("gmtPublishEnd", gmtPublishEnd);

		context.put("page", page);
		context.put("pageSize", pageSize);
		int totalCount = buyProductService.queryCount(productQuery);
		context.put("totalCount", totalCount);
		context.put("totalPage", (totalCount - 1) / pageSize + 1);
		List<BuyProduct> productList = buyProductService.query(productQuery,
				(page - 1) * pageSize, pageSize);
		context.put("productList", wrapBuyProductVO(productList));

		// �����Ϣ����Ϊ��ѯ������
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

	private List<BuyProductVO> wrapBuyProductVO(List<BuyProduct> productList) {
		List<BuyProductVO> resList = new ArrayList<BuyProductVO>();
		for (BuyProduct p : productList) {
			BuyProductVO proVo = new BuyProductVO();
			proVo.setId(p.getId());
			proVo.setImgs(p.getImgs());
			proVo.setDescription(p.getDescription());
			proVo.setLowestDealSize(p.getLowestDealSize());
			proVo.setName(p.getName());
			proVo.setPrice(p.getPrice());
			proVo.setUnit(p.getUnit());
			proVo.setQuantity(p.getQuantity());
			proVo.setStatus(p.getStatus());
			User user = userService.getUserByLoginId(p.getOwner());
			proVo.setCompany(user.getCompany());
			ProductCategory category = productCategoryService.getCategoryById(p
					.getCategoryId());
			proVo.setCategory(category.getName());
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