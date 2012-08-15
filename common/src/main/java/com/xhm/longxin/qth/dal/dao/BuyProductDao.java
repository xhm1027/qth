/**
 *
 */
package com.xhm.longxin.qth.dal.dao;
import java.util.List;

import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.Message;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;
import com.xhm.longxin.qth.dal.query.MessageQuery;


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

	/**
	 * 根据id取记录
	 * */
	public BuyProduct getBuyProductById(Long id);
	/**
	 * 查询记录
	 * */
	public List<BuyProduct> query(BuyProductQuery buyProductQuery);
	/**
	 * 分页查询记录
	 * */
	public List<BuyProduct> query(BuyProductQuery buyProductQuery,int pageStart,int pageSize);
	/**
	 * 记录数
	 * */
	public int queryCount(BuyProductQuery buyProductQuery);
}
