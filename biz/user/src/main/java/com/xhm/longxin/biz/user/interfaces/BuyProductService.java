/**
 *
 */
package com.xhm.longxin.biz.user.interfaces;


import java.util.List;

import com.xhm.longxin.biz.user.vo.AuditProductVO;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.dataobject.SaleProduct;
import com.xhm.longxin.qth.dal.dataobject.User;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;
import com.xhm.longxin.qth.dal.query.SaleProductQuery;

/**
 * @author xhm.xuhm
 *
 */
public interface BuyProductService {

	public boolean addBuyProduct(BuyProduct buyProduct);
	/**
	 * ����idȡ��¼
	 * */
	public BuyProduct getBuyProductById(Long id);

	/**
	 * ���¼�¼
	 * */
	public boolean updateBuyProduct(BuyProduct product);

	public List<BuyProduct> query(BuyProductQuery buyProductQuery);

	public List<BuyProduct> query(BuyProductQuery buyProductQuery,int pageStart,int pageSize);

	public int queryCount(BuyProductQuery buyProductQuery);
	/**
	 * ɾ����¼
	 * */
	public boolean deleteBuyProductById(Long id);

	/**
	 * ��˲�Ʒ
	 * */
	public boolean auditBuyProductById(AuditProductVO auditVO);
	/**
	 * �ϼ�
	 * */
	public boolean onShelf(Long id,User user);
	/**
	 * �¼�
	 * */
	public boolean offShelf(Long id,User user);

}
