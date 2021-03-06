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
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.dal.query.CategoryQuery;
import com.xhm.longxin.qth.dal.query.QueryObject;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;
import com.xhm.longxin.qth.web.user.vo.ProductCategoryVO;
import com.xhm.longxin.qth.web.user.vo.SaleProductVO;

public class ListSaleProduct {
	@Autowired
	private SaleProductService saleProductService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	HttpSession session;
	public void execute(@Param(name = "name") String name,
			@Param(name = "notFirst") String notFirst,
			@Param(name = "m") Long[] m,
			@Param(name = "nm") Long[] nm,
			@Param(name = "order") String order,
			@Param(name = "orderDesc") String orderDesc,
			@Param(name = "page") int page,
			@Param(name = "pageSize") int pageSize, Context context) {
		// 如果是第一次请求，把兴趣类别默认勾选！
		if (StringUtil.isEmpty(notFirst)) {
			List<ProductCategory> resourceCategoryList = productCategoryService
					.getAllResourceCategory();
			List<ProductCategory> materialCategoryList = productCategoryService
					.getAllMaterialCategory();
			QthUser qthUser = (QthUser) session
					.getAttribute(UserConstant.QTH_USER_SESSION_KEY);
			if (qthUser == null || qthUser.getId() == null) {
				// 如果未登录，类别全选
				m = new Long[materialCategoryList.size()];
				for (int i = 0; i < materialCategoryList.size(); i++) {
					m[i] = materialCategoryList.get(i).getId();
				}
				nm = new Long[resourceCategoryList.size()];
				for (int i = 0; i < resourceCategoryList.size(); i++) {
					nm[i] = resourceCategoryList.get(i).getId();
				}
			} else {
				// 如果已登录，则先把兴趣取出来（这里列是的卖的产品，也即用户要有采购兴趣的产品）
				List<Long> materialIdList = new ArrayList<Long>();
				List<Long> resourceIdList = new ArrayList<Long>();
				User user = userService.getUserByLoginId(qthUser.getId());
				if (user != null && user.getBuyInterests() != null) {
					for (UserInterest interest : user.getBuyInterests()) {
						for (int i = 0; i < resourceCategoryList.size(); i++) {
							if(interest.getValue().equals(resourceCategoryList.get(i).getId())){
								resourceIdList.add(interest.getValue());
							}
						}
						for (int i = 0; i < materialCategoryList.size(); i++) {
							if(interest.getValue().equals(materialCategoryList.get(i).getId())){
								materialIdList.add(interest.getValue());
							}
						}
					}
				}
				m=new Long[materialIdList.size()];
				materialIdList.toArray(m);
				nm=new Long[resourceIdList.size()];
				resourceIdList.toArray(nm);
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
		productQuery.setName(name);
		//排序方式
		if(orderDesc!=null&&orderDesc.equals(QueryObject.ORDER_DESC)){
			productQuery.setOrderDesc(true);
		}
		if(orderDesc!=null&&orderDesc.equals(QueryObject.ORDER_ASC)){
			productQuery.setOrderAsc(true);
		}
		// 用户冻结和审核失败时，需要扩展字段来进行描述标记，不能直接用SaleProduct
		productQuery.setStatus(ProductStatus.ON_SHELF);
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
		// 排序
		if (order != null && order.equals("price")) {
			productQuery.setOrderByPrice(true);
		}
		if (order != null && order.equals("publish")) {
			productQuery.setOrderModified(true);
		}
		// 查询参数
		context.put("m", m);
		context.put("nm", nm);
		context.put("name", name);
		context.put("order", order);
		context.put("page", page);
		context.put("pageSize", pageSize);
		context.put("orderDesc", orderDesc);
		context.put("notFirst", "notFirst");//只要查过，下次就会带这个参数
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
			proVo.setGmtModified(p.getGmtModified());
			proVo.setUnit(p.getUnit());
			proVo.setQuantity(p.getQuantity());
			User user = userService.getUserByLoginId(p.getOwner());
			proVo.setOwnerStatus(user.getStatus());
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
