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

package com.xhm.longxin.qth.web.user.module.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;

public class Register {
	@Autowired
	private ProductCategoryService productCategoryService;
	
	public void execute( Context context) {
		/* ��Ϊע��Ķ���԰���ⲿ�û������Գ�ʼ������Ȥ�Ĳɹ���Ʒ��Ŀ����Դ��Ʒ����Ŀ ,����Ȥ��������Ŀ����ԭ���ϲ�Ʒ����Ŀ*/
		List<ProductCategory> buyInterests = productCategoryService.getAllResourceCategory();
		List<ProductCategory> sellInterests = productCategoryService.getAllMaterialCategory();
		
		context.put("buyInterests", buyInterests);
		context.put("sellInterests", sellInterests);
	}
}
