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

package com.xhm.longxin.qth.web.user.module.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.CustomErrors;
import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.service.requestcontext.parser.ParserRequestContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormField;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.xhm.longxin.biz.user.interfaces.FileService;
import com.xhm.longxin.biz.user.interfaces.ProductCategoryService;
import com.xhm.longxin.biz.user.interfaces.SaleProductService;
import com.xhm.longxin.biz.user.interfaces.UserService;
import com.xhm.longxin.qth.dal.constant.AttachmentType;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.constant.ProductType;
import com.xhm.longxin.qth.dal.constant.UserInterestType;
import com.xhm.longxin.qth.dal.constant.UserLevel;
import com.xhm.longxin.qth.dal.constant.UserStatus;
import com.xhm.longxin.qth.dal.dataobject.Attachment;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.web.user.common.QthUser;
import com.xhm.longxin.qth.web.user.common.UserConstant;

public class SaleProductAction {
	@Autowired
	private UserService userService;

	@Autowired
	private SaleProductService saleProductService;

	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	HttpSession session;
	@Autowired
	ParserRequestContext parser;

	@Autowired
	FileService fileService;

	public void doAdd(@FormGroup("addSaleProduct") SaleProduct saleProduct,
			@Param("priceOnface") String priceOnface,
			@FormField(name = "addSaleProductError", group = "addSaleProduct") CustomErrors err,
			Navigator nav, ParameterParser params, Context context) {
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);
		if(qthUser==null){
			nav.redirectTo(UserConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}
		User user = userService.getUserByLoginId(qthUser.getId());
		if(user==null){
			nav.redirectTo(UserConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}

		if(UserStatus.NORMAL.equalsIgnoreCase(user.getStatus())==false){ //其他状态不能发布产品
			err.setMessage("noPermissionFail");
			return;
		}

		saleProduct.setOwner(qthUser.getId());//设置所属用户
		ProductCategory pc = productCategoryService.getCategoryById(saleProduct.getCategoryId());
		if(IS.Y.equalsIgnoreCase(pc.getIsMaterial())){//判断产品类型
			saleProduct.setProductType(ProductType.MATERIAL);
		}else{
			saleProduct.setProductType(ProductType.RESOURCE);
		}
		if(UserLevel.GOLDEN.equalsIgnoreCase(user.getUserLevel())){//判断产品状态
			saleProduct.setStatus(ProductStatus.ON_SHELF);
			//saleProduct.setIsSale(IS.Y);
		}else{
			saleProduct.setStatus(ProductStatus.NEW);
			//saleProduct.setIsSale(IS.N);
		}
		List<Attachment> imgs=new ArrayList<Attachment>();

		FileItem[] imageItems = parser.getParameters().getFileItems("productImages");
		try{
			for(FileItem file:imageItems){
				String path = fileService.saveFile(file, user.getId().toString());
				Attachment image=new Attachment();
				image.setPath(path);
				image.setType(AttachmentType.IMG);
				image.setKey(UserInterestType.SALE);
				imgs.add(image);
			}
		}catch(Exception e){
			err.setMessage("saveImageFail");
			return;
		}
		saleProduct.setImgs(imgs);

		if(priceOnface!=null){//处理面议情况
			saleProduct.setPrice(-1d);
		}
		
		boolean result = saleProductService.addSaleProduct(saleProduct);
		if(result){
			context.put("result", "success");
		}else{
			err.setMessage("addSaleProductFail");
		}
	}


	public void doEdit(@FormGroup("editSaleProduct") SaleProduct saleProduct,
			@Param("priceOnface") String priceOnface,
			@FormField(name = "editSaleProductError", group = "editSaleProduct") CustomErrors err,
			Navigator nav, ParameterParser params, Context context) {
		QthUser qthUser = (QthUser) session
				.getAttribute(UserConstant.QTH_USER_SESSION_KEY);
		if(qthUser==null){
			nav.redirectTo(UserConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}
		User user = userService.getUserByLoginId(qthUser.getId());
		if(user==null){
			nav.redirectTo(UserConstant.LOGIN_RETURN_DEFAULT_LINK);
			return;
		}

		if(UserStatus.NORMAL.equalsIgnoreCase(user.getStatus())==false){ //其他状态不能发布产品
			err.setMessage("noPermissionFail");
			return;
		}
		SaleProduct saleProductInDatabase = saleProductService.getSaleProductById(saleProduct.getId());//查找产品是否存在
		if(saleProductInDatabase==null){
			err.setMessage("editSaleProductError");
			return;
		}

		saleProduct.setOwner(qthUser.getId());//设置所属用户
		saleProduct.setProductType(saleProductInDatabase.getProductType());

		if(UserLevel.GOLDEN.equalsIgnoreCase(user.getUserLevel())){//判断产品状态
			saleProduct.setStatus(ProductStatus.ON_SHELF);
			//saleProduct.setIsSale(IS.Y);
		}else{
			saleProduct.setStatus(ProductStatus.NEW);
			//saleProduct.setIsSale(IS.N);
		}
		List<Attachment> imgs=saleProductInDatabase.getImgs();

		FileItem[] imageItems = parser.getParameters().getFileItems("productImages");
		try{
			for(FileItem file:imageItems){
				String path = fileService.saveFile(file, user.getId().toString());
				Attachment image=new Attachment();
				image.setPath(path);
				image.setType(AttachmentType.IMG);
				image.setKey(UserInterestType.SALE);
				image.setOwnerId(saleProduct.getId());
				imgs.add(image);
			}
		}catch(Exception e){
			err.setMessage("saveImageFail");
			return;
		}
		saleProduct.setImgs(imgs);

		if(priceOnface!=null){//处理面议情况
			saleProduct.setPrice(-1d);
		}
		
		boolean result = saleProductService.updateSaleProduct(saleProduct);
		if(result){
			context.put("result", "success");
			context.put("resultMessage", "编辑产品成功！");
		}else{
			err.setMessage("editSaleProductFail");
		}
	}


}
