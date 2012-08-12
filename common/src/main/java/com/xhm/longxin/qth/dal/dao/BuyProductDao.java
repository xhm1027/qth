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
	 * 新增记录
	 * */
	public boolean addBuyProduct(BuyProduct product);

	/**
	 * 更新记录
	 * */
	public boolean updateBuyProduct(BuyProduct product);

	/**
	 * 删除记录
	 * */
	public boolean deleteBuyProductById(Long id);
}
