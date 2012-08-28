/**
 *
 */
package com.xhm.longxin.biz.user.interfaces;


import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;

/**
 * @author xhm.xuhm
 *
 */
public interface BuyProductService {

	public boolean addBuyProduct(BuyProduct buyProduct);
	/**
	 * 根据id取记录
	 * */
	public BuyProduct getBuyProductById(Long id);

	/**
	 * 更新记录
	 * */
	public boolean updateBuyProduct(BuyProduct product);

	public List<BuyProduct> query(BuyProductQuery buyProductQuery);

	public List<BuyProduct> query(BuyProductQuery buyProductQuery,int pageStart,int pageSize);

	public int queryCount(BuyProductQuery buyProductQuery);

}
