package com.xhm.longxin.biz.user.interfaces;

import java.util.List;

import com.xhm.longxin.biz.user.vo.AuditProductVO;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;

public interface SaleProductService {
	/**
	 * 新增记录
	 * */
	public boolean addSaleProduct(SaleProduct product);

	/**
	 * 更新记录
	 * */
	public boolean updateSaleProduct(SaleProduct product);

	/**
	 * 删除记录
	 * */
	public boolean deleteSaleProductById(Long id);

	/**
	 * 查询记录
	 * */
	public List<SaleProduct> query(SaleProductQuery saleProductQuery);
	/**
	 * 分页查询记录
	 * */
	public List<SaleProduct> query(SaleProductQuery saleProductQuery,int pageStart,int pageSize);
	/**
	 * 记录数
	 * */
	public int queryCount(SaleProductQuery saleProductQuery);
	/**
	 * 根据id取记录
	 * */
	public SaleProduct getSaleProductById(Long id);

	/**
	 * 审核产品
	 * */
	public boolean auditSaleProductById(AuditProductVO auditVO);
	/**
	 * 上架
	 * */
	public boolean onShelf(Long id,User user);
	/**
	 * 下架
	 * */
	public boolean offShelf(Long id,User user);

}
