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

	/**
	 * ����idȡ��¼
	 * */
	public BuyProduct getBuyProductById(Long id);
	/**
	 * ��ѯ��¼
	 * */
	public List<BuyProduct> query(BuyProductQuery buyProductQuery);
	/**
	 * ��ҳ��ѯ��¼
	 * */
	public List<BuyProduct> query(BuyProductQuery buyProductQuery,int pageStart,int pageSize);
	/**
	 * ��¼��
	 * */
	public int queryCount(BuyProductQuery buyProductQuery);
}
