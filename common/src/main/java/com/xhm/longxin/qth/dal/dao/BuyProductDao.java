/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;

/**
 * @author ren.zhangr
 *
 */
public interface BuyProductDao {
	/**
	 * ������¼
	 * */
	public boolean addBuyProduct(BuyProduct product);

	/**
	 * ���¼�¼
	 * */
	public boolean updateBuyProduct(BuyProduct product);

	/**
	 * ɾ����¼
	 * */
	public boolean deleteBuyProductById(Long id);
}
