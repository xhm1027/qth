/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;

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

	/**
	 * ��ѯ��¼
	 * */
	public List<SaleProduct> query(SaleProductQuery saleProductQuery);
	/**
	 * ��ҳ��ѯ��¼
	 * */
	public List<SaleProduct> query(SaleProductQuery saleProductQuery,int pageStart,int pageSize);
	/**
	 * ��¼��
	 * */
	public int queryCount(SaleProductQuery saleProductQuery);
	/**
	 * ����idȡ��¼
	 * */
	public SaleProduct getSaleProductById(Long id);
}
