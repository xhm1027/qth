/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;

/**
 * @author ren.zhangr
 *
 */
public interface SaleProductDao {
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
}
