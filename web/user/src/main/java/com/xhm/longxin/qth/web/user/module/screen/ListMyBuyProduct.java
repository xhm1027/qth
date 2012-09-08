package com.xhm.longxin.qth.web.user.module.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.util.StringUtil;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.BuyProductService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.dal.query.CategoryQuery;
import com.xhm.longxin.qth.dal.query.QueryObject;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;
import com.xhm.longxin.qth.web.user.vo.ProductCategoryVO;
import com.xhm.longxin.qth.web.user.vo.BuyProductVO;

public class ListMyBuyProduct {
	@Autowired
	private BuyProductService buyProductService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	HttpSession session;

	public void execute(@Param(name = "name") String name,
			@Param(name = "status") String status,
			@Param(name = "notFirst") String notFirst,
			@Param(name = "m") Long[] m, @Param(name = "nm") Long[] nm,
			@Param(name = "page") int page,
			@Param(name = "pageSize") int pageSize, Context context) {
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);
		if (qthUser == null || qthUser.getId() == null) {
			return;
		}
		// 如果是第一次请求，把兴趣类别默认勾选！
		if (StringUtil.isEmpty(notFirst)) {
			List<ProductCategory> resourceCategoryList = productCategoryService
					.getAllResourceCategory();
			List<ProductCategory> materialCategoryList = productCategoryService
					.getAllMaterialCategory();
			// 用户已登录，把兴趣取出来（这里是列的是自己自己的求购产品，即用户有求购的兴趣的产品类目要选中）
			List<Long> materialIdList = new ArrayList<Long>();
			List<Long> resourceIdList = new ArrayList<Long>();
			User user = userService.getUserByLoginId(qthUser.getId());
			if (user != null && user.getBuyInterests() != null) {
				for (UserInterest interest : user.getBuyInterests()) {
					for (int i = 0; i < resourceCategoryList.size(); i++) {
						if (interest.getValue().equals(
								resourceCategoryList.get(i).getId())) {
							resourceIdList.add(interest.getValue());
						}
					}
					for (int i = 0; i < materialCategoryList.size(); i++) {
						if (interest.getValue().equals(
								materialCategoryList.get(i).getId())) {
							materialIdList.add(interest.getValue());
						}
					}
				}
			}
			m = new Long[materialIdList.size()];
			materialIdList.toArray(m);
			nm = new Long[resourceIdList.size()];
			resourceIdList.toArray(nm);
		}
		// 分页参数处理
		BuyProductQuery productQuery = new BuyProductQuery();
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.defaultPageSize;
		}
		productQuery.setName(name);
		// 用户冻结和审核失败时，需要扩展字段来进行描述标记，不能直接用BuyProduct
		productQuery.setStatus(status);
		productQuery.setOwner(qthUser.getId());
		// 采购类别
		List<Long> categoryIds = new ArrayList<Long>();
		List<Long> mIds = Arrays.asList(m);
		for (Long id : mIds) {
			categoryIds.add(id);
		}
		List<Long> nmCIds = Arrays.asList(nm);
		for (Long id : nmCIds) {
			categoryIds.add(id);
		}
		if (categoryIds.size() > 0) {
			productQuery.setCategoryIds(categoryIds);
		}
		// 查询参数
		context.put("m", m);
		context.put("nm", nm);
		context.put("name", name);
		context.put("status", status);
		context.put("notFirst", "notFirst");//只要查过，下次就会带这个参数
		context.put("page", page);
		context.put("pageSize", pageSize);
		int totalCount = buyProductService.queryCount(productQuery);
		context.put("totalCount", totalCount);
		context.put("totalPage", (totalCount - 1) / pageSize + 1);
		List<BuyProduct> productList = buyProductService.query(productQuery,
				(page - 1) * pageSize, pageSize);
		context.put("productList", wrapBuyProductVO(productList));

		// 类别信息，作为查询条件用
		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setIsMaterial(IS.Y);
		context.put("materialCategories", wrapProductCategoryVO(
				productCategoryService.query(categoryQuery), Arrays.asList(m),
				null));
		categoryQuery.setIsMaterial(IS.N);
		context.put("notMaterialCategories", wrapProductCategoryVO(
				productCategoryService.query(categoryQuery), null, Arrays
						.asList(nm)));
	}

	private List<BuyProductVO> wrapBuyProductVO(List<BuyProduct> productList) {
		List<BuyProductVO> resList = new ArrayList<BuyProductVO>();
		for (BuyProduct p : productList) {
			BuyProductVO proVo = new BuyProductVO();
			proVo.setId(p.getId());
			proVo.setImgs(p.getImgs());
			proVo.setStatus(p.getStatus());
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
