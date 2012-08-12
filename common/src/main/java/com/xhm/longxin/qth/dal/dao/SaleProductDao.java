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
	 * ������¼
	 * */
	public boolean addSaleProduct(SaleProduct product);

	/**
	 * ���¼�¼
	 * */
	public boolean updateSaleProduct(SaleProduct product);

	/**
	 * ɾ����¼
	 * */
	public boolean deleteSaleProductById(Long id);
}
