/**
 *
 */
package com.xhm.longxin.qth.web.user.module.screen;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.util.StringUtil;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.constant.UserRole;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.dal.query.CategoryQuery;
import com.xhm.longxin.qth.dal.query.QueryObject;
import com.xhm.longxin.qth.dal.query.UserQuery;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;
import com.xhm.longxin.qth.web.user.vo.ProductCategoryVO;

/**
 * @author ren.zhangr
 *
 */
public class ListEnterprise {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	HttpSession session;

	public void execute(@Param(name = "company") String company,
			@Param(name = "b") Long[] b, @Param(name = "s") Long[] s,
			@Param(name = "order") String order,
			@Param(name = "orderDesc") String orderDesc,
			@Param(name = "page") int page,
			@Param(name = "pageSize") int pageSize,
			@Param(name = "userRole") String userRole, Context context) {
		// 如果是第一次请求，全部勾选
		if ((b == null || b.length == 0) && (s == null || s.length == 0)) {
			List<ProductCategory> resourceCategoryList = productCategoryService
					.getAllResourceCategory();
			List<ProductCategory> materialCategoryList = productCategoryService
					.getAllMaterialCategory();
			if (UserRole.INNER_USER.equals(userRole)) {// 内部用户b是原材料类别
				b = new Long[materialCategoryList.size()];
				for (int i = 0; i < materialCategoryList.size(); i++) {
					b[i] = materialCategoryList.get(i).getId();
				}
				s = new Long[resourceCategoryList.size()];
				for (int i = 0; i < resourceCategoryList.size(); i++) {
					s[i] = resourceCategoryList.get(i).getId();
				}
			} else {
				s = new Long[materialCategoryList.size()];
				for (int i = 0; i < materialCategoryList.size(); i++) {
					s[i] = materialCategoryList.get(i).getId();
				}
				b = new Long[resourceCategoryList.size()];
				for (int i = 0; i < resourceCategoryList.size(); i++) {
					b[i] = resourceCategoryList.get(i).getId();
				}
			}
		}
		// 分页参数处理
		UserQuery userQuery = new UserQuery();
		if (page == 0) {
			page = 1;
		}
		if (pageSize == 0 || pageSize > QueryObject.maxPageSize) {
			pageSize = QueryObject.defaultPageSize;
		}

		// 企业类型
		if (UserRole.INNER_USER.equalsIgnoreCase(userRole)) {
			userQuery.setRole(UserRole.INNER_USER);
		} else {
			userQuery.setRole(UserRole.OUTER_USER);
		}
		// 公司名
		userQuery.setCompany(company);
		// 采购类别
		List<Long> buyInterestIds = null;
		if (b != null && b.length > 0) {
			buyInterestIds = new ArrayList<Long>();
			for (Long id : b) {
				buyInterestIds.add(id);
			}
			userQuery.setBuyInterestIds(buyInterestIds);
		}
		// 销售类别
		List<Long> saleInterestIds = null;
		if (s != null && s.length > 0) {
			saleInterestIds = new ArrayList<Long>();
			for (Long id : s) {
				saleInterestIds.add(id);
			}
			userQuery.setSaleInterestIds(saleInterestIds);
		}
		// 排序
		if (order != null) {
			userQuery.setOrderModified(true);
			// 排序方式
			if (orderDesc != null && orderDesc.equals(QueryObject.ORDER_DESC)) {
				userQuery.setOrderDesc(true);
			}
			if (orderDesc != null && orderDesc.equals(QueryObject.ORDER_ASC)) {
				userQuery.setOrderAsc(true);
			}
		}
		// 查询结果
		context.put("userList", userService.queryUser(userQuery, (page - 1)
				* pageSize, pageSize));
		// 查询参数
		context.put("buyInterestIds", b);
		context.put("saleInterestIds", s);
		context.put("company", company);
		context.put("order", order);
		context.put("userRole", userRole);
		context.put("page", page);
		context.put("orderDesc", orderDesc);
		context.put("pageSize", pageSize);
		int totalCount = userService.queryCount(userQuery);
		context.put("totalCount", totalCount);
		context.put("totalPage", (totalCount - 1) / pageSize + 1);

		// 类别信息，作为查询条件用
		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setIsMaterial(IS.Y);
		context.put("materialCategories", wrapProductCategoryVO(
				productCategoryService.query(categoryQuery), saleInterestIds,
				buyInterestIds));
		categoryQuery.setIsMaterial(IS.N);
		context.put("notMaterialCategories", wrapProductCategoryVO(
				productCategoryService.query(categoryQuery), saleInterestIds,
				buyInterestIds));
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
