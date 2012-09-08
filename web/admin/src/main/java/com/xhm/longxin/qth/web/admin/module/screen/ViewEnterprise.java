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
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.UserRole;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.vo.InterestCategoryVO;
import com.xhm.longxin.qth.web.admin.common.AdminConstant;
import com.xhm.longxin.qth.web.admin.common.QthAdmin;

/**
 * @author ren.zhangr
 *
 */
public class ViewEnterprise {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductCategoryService productCategoryService;

	public void execute(@Param("id") Long id,Navigator nav, HttpSession session, Context context) {
		List<ProductCategory> resourceCategoryList = productCategoryService
				.getAllResourceCategory();
		List<ProductCategory> materialCategoryList = productCategoryService
				.getAllMaterialCategory();
		//未登录，返回
		QthAdmin qthAdmin = (QthAdmin) session
		.getAttribute(AdminConstant.QTH_ADMIN_SESSION_KEY);
		if (qthAdmin == null||!qthAdmin.hasLoggedIn()) {
			return;
		}
//		/企业信息不存在
		User user = userService.getUserById(id);
		if (user == null) {
			return;
		}
		List<InterestCategoryVO> buyInterestList = new ArrayList<InterestCategoryVO>();
		List<InterestCategoryVO> sellInterestList = new ArrayList<InterestCategoryVO>();
		if (UserRole.OUTER_USER.equals(user.getRole())) {// 外部用户，感兴趣的采购类别是资源，销售类别是原材料
			for (ProductCategory c : resourceCategoryList) {
				InterestCategoryVO vo = new InterestCategoryVO();
				vo.setCategory(c);
				vo.setHas(this.isCategoryInInterestList(c, user
						.getBuyInterests()));
				if (vo.isHas()) {
					buyInterestList.add(vo);
				}
			}
			for (ProductCategory c : materialCategoryList) {
				InterestCategoryVO vo = new InterestCategoryVO();
				vo.setCategory(c);
				vo.setHas(this.isCategoryInInterestList(c, user
						.getSaleInterests()));
				if (vo.isHas()) {
					sellInterestList.add(vo);
				}
			}
		} else {// 内部用户，感兴趣的是采购类别是原材料，销售类别是资源
			for (ProductCategory c : materialCategoryList) {
				InterestCategoryVO vo = new InterestCategoryVO();
				vo.setCategory(c);
				vo.setHas(this.isCategoryInInterestList(c, user
						.getBuyInterests()));
				if (vo.isHas()) {
					buyInterestList.add(vo);
				}
			}
			for (ProductCategory c : resourceCategoryList) {
				InterestCategoryVO vo = new InterestCategoryVO();
				vo.setCategory(c);
				vo.setHas(this.isCategoryInInterestList(c, user
						.getSaleInterests()));
				if (vo.isHas()) {
					sellInterestList.add(vo);
				}
			}
		}

		context.put("user", user);
		context.put("buyInterestList", buyInterestList);
		context.put("sellInterestList", sellInterestList);
	}

	/**
	 * 产品类别是否在兴趣列表中
	 *
	 * @param category
	 * @param userInterestList
	 * @return
	 */
	private boolean isCategoryInInterestList(ProductCategory category,
			List<UserInterest> userInterestList) {
		for (UserInterest userInterest : userInterestList) {
			if (userInterest.getValue().longValue() == category.getId()
					.longValue()) {
				return true;
			}
		}
		return false;
	}
}
