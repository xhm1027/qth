/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.jtester.annotations.SpringBeanByName;
import org.jtester.core.IJTester.DataGenerator;
import org.jtester.core.IJTester.DataMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xhm.longxin.qth.dal.constant.AttachmentType;
import com.xhm.longxin.qth.dal.constant.Gender;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.constant.ProductStatus;
import com.xhm.longxin.qth.dal.constant.ProductType;
import com.xhm.longxin.qth.dal.constant.UserLevel;
import com.xhm.longxin.qth.dal.constant.UserRole;
import com.xhm.longxin.qth.dal.constant.UserStatus;
import com.xhm.longxin.qth.dal.dataobject.Attachment;
import com.xhm.longxin.qth.dal.dataobject.BuyProduct;
import com.xhm.longxin.qth.dal.query.BuyProductQuery;

/**
 * @author ren.zhangr
 *
 */
public class BuyProductDaoTest  extends BaseDaoTest {
	@SpringBeanByName
	private BuyProductDao buyProductDao;

	@BeforeMethod
	public void setUp(){
		db.table("qth_user").clean().insert(6, new DataMap() {
			{
				this.put("login_id", DataGenerator.repeat("zhangren","xhm","admin","zhangren1","xhm1","admin1"));
				this.put("name", DataGenerator.repeat("����1","����2","����3","����4","����5","����6"));
				this.put("gender",Gender.FEMALE );
				this.put("email", "abc@abc.com");
				this.put("phone_area", "0571");
				this.put("phone_number", "85022088");
				this.put("company", DataGenerator.repeat("��˾1","��˾2","��˾3","��˾4","��˾5","��˾6"));
				this.put("role", UserRole.INNER_USER);
				this.put("user_level", UserLevel.GOLDEN);
				this.put("status", UserStatus.NEW);
				this.put("is_deleted", IS.N);
			}
		}).commit();
		db.table("qth_product_buy").clean().insert(3, new DataMap() {
			{
				this.put("id",DataGenerator.repeat(1,2,3));
				this.put("name", DataGenerator.repeat("���Բ�Ʒ1","���Բ�Ʒ2","���Բ�Ʒ3"));
				this.put("category_id", 1);
				this.put("is_sale", DataGenerator.repeat("Y","Y","N"));
				this.put("product_type", ProductType.RESOURCE);
				this.put("is_deleted", "N");
				this.put("status", ProductStatus.NEW);
				this.put("owner", DataGenerator.repeat("zhangren","xhm"));
			}
		}).commit();
		db.table("qth_pro_category").clean().insert(3, new DataMap() {
			{
				this.put("id",DataGenerator.repeat(1,2,3));
				this.put("name", DataGenerator.repeat("�������1","�������2","�������3"));
				this.put("description", "������Ϣxxx");
				this.put("is_material", DataGenerator.repeat("Y","Y","N"));
				this.put("is_deleted", "N");
			}
		}).commit();
		db.table("qth_attachment").clean().insert(3, new DataMap() {
			{
				this.put("owner_id",DataGenerator.repeat(1,1,1));
				this.put("type", "image");
				this.put("path", DataGenerator.repeat("/test/img1.jpb","/test/img2.jpb","/test/img3.jpb"));
				this.put("is_deleted", "N");
				this.put("key", "buy");
			}
		}).commit();
	}
	@Test
	public void testAddProduct() {
		db.table("qth_product_buy").clean().commit();
		BuyProduct product = new BuyProduct();
		product.setOwner("zhangren");
		product.setName("��Ԫ���Բ�Ʒ����");
		product.setQuantity(12345L);
		product.setStatus(ProductStatus.NEW);
		product.setCategoryId(123456L);
		List<Attachment> imgs=new ArrayList<Attachment>();
		Attachment image1=new Attachment();
		image1.setPath("/test/img.jpb");
		image1.setType(AttachmentType.IMG);
		imgs.add(image1);
		product.setImgs(imgs);

		want.bool(buyProductDao.addBuyProduct(product)).eq(true);
		BuyProductQuery buyProductQuery=new BuyProductQuery();
		List<Long> categoryIds=new ArrayList<Long>();
		categoryIds.add(123456L);
		buyProductQuery.setCategoryIds(categoryIds);
		//��ͨ��ѯ�����Ϊ1��
		List<BuyProduct> list=buyProductDao.query(buyProductQuery);
		want.number(list.size()).eq(1);

	}

	@Test
	public void testQueryProduct() {
		//��ѯ�ռ���Ϊs1����Ϣ
		BuyProductQuery buyProductQuery=new BuyProductQuery();
		List<Long> categoryIds=new ArrayList<Long>();
		categoryIds.add(1L);
		buyProductQuery.setCategoryIds(categoryIds);
		//��ͨ��ѯ�����Ϊ1��
		List<BuyProduct> list=buyProductDao.query(buyProductQuery);
		want.number(list.size()).eq(3);
		//��ҳ��ѯ
		list=buyProductDao.query(buyProductQuery,0,2);
		want.number(list.size()).eq(2);
		//��name��ѯ
		buyProductQuery.setName("��Ʒ1");
		list=buyProductDao.query(buyProductQuery);
		want.number(list.size()).eq(1);
		//��鸽����ѯʱ�������
		BuyProduct pro=list.get(0);
		want.number(pro.getImgs().size()).eq(3);
	}

	@Test
	public void testUpdateProduct() {
		BuyProductQuery buyProductQuery=new BuyProductQuery();
		buyProductQuery.setName("��Ʒ1");
		List<BuyProduct> list=buyProductDao.query(buyProductQuery);
		want.number(list.size()).eq(1);
		//��Ҫ���Ի�ͼƬ
		BuyProduct bp=list.get(0);
		List<Attachment> imgs=bp.getImgs();
		Attachment attaDeleted=imgs.get(0);
		imgs.remove(0);
		Attachment img=new Attachment();
		img.setOwnerId(1L);
		img.setKey("buy");
		img.setType(AttachmentType.IMG);
		img.setPath("/test/img4.jpb");
		imgs.add(img);
		bp.setImgs(imgs);
		buyProductDao.updateBuyProduct(bp);
		BuyProduct ubp=buyProductDao.getBuyProductById(bp.getId());
		//��������3��
		want.number(ubp.getImgs().size()).eq(3);
		boolean res=false;
		//�µ�һ���ӽ�ȥ��
		for(Attachment att:ubp.getImgs()){
			if(att.getPath().equalsIgnoreCase("/test/img4.jpb")){
				res=true;
				break;
			}
		}
		want.bool(res).eq(true);
		//�ϵ�һ��ɾ����
		res=true;
		for(Attachment att:ubp.getImgs()){
			if(att.getPath().equalsIgnoreCase(attaDeleted.getPath())){
				res=false;
				break;
			}
		}
		want.bool(res).eq(true);
	}
}
