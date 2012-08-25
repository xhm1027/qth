/**
 *
 */
package com.xhm.longxin.biz.user.interfaces;


import com.xhm.longxin.qth.dal.dataobject.BuyProduct;

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

	
}
