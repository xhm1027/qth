/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xhm.longxin.qth.web.admin.module.screen;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.UserRole;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.dataobject.UserInterest;
import com.xhm.longxin.qth.vo.InterestCategoryVO;

public class EditUser {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductCategoryService productCategoryService;
	
	public void execute(@Param(name = "id") Long id,Navigator nav,HttpSession session, Context context) {
	
		List<ProductCategory> resourceCategoryList = productCategoryService.getAllResourceCategory();
		List<ProductCategory> materialCategoryList = productCategoryService.getAllMaterialCategory();
	
		User user = userService.getUserById(id);
		if(user==null){
			context.put("result", "error");
			context.put("resultMessage", "用户不存在");
			return;
		}
		List<InterestCategoryVO> buyInterestList = new ArrayList<InterestCategoryVO>();
		List<InterestCategoryVO> sellInterestList = new ArrayList<InterestCategoryVO>();
		if(UserRole.OUTER_USER.equals(user.getRole())){//外部用户，感兴趣的采购类别是资源，销售类别是原材料
			for(ProductCategory c: resourceCategoryList){
				InterestCategoryVO vo = new InterestCategoryVO();
				vo.setCategory(c);
				vo.setHas(this.isCategoryInInterestList(c, user.getBuyInterests()));
				
				buyInterestList.add(vo);
			}
			for(ProductCategory c: materialCategoryList){
				InterestCategoryVO vo = new InterestCategoryVO();
				vo.setCategory(c);
				vo.setHas(this.isCategoryInInterestList(c, user.getSaleInterests()));
				
				sellInterestList.add(vo);
			}
		}else{//内部用户，感兴趣的是采购类别是原材料，销售类别是资源
			for(ProductCategory c: materialCategoryList){
				InterestCategoryVO vo = new InterestCategoryVO();
				vo.setCategory(c);
				vo.setHas(this.isCategoryInInterestList(c, user.getBuyInterests()));
				
				buyInterestList.add(vo);
			}
			for(ProductCategory c: resourceCategoryList){
				InterestCategoryVO vo = new InterestCategoryVO();
				vo.setCategory(c);
				vo.setHas(this.isCategoryInInterestList(c, user.getSaleInterests()));
				
				sellInterestList.add(vo);
			}
		}
		
		context.put("user", user);
		context.put("buyInterestList", buyInterestList);
		context.put("sellInterestList", sellInterestList);
	}
	
	
	/**
	 * 产品类别是否在兴趣列表中
	 * @param category
	 * @param userInterestList
	 * @return
	 */
	private boolean isCategoryInInterestList(ProductCategory category,List<UserInterest> userInterestList){
		for(UserInterest userInterest:userInterestList){
			if(userInterest.getValue().longValue()==category.getId().longValue()){
				return true;
			}
		}
		return false;
	}
}
