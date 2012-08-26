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
				this.put("name", DataGenerator.repeat("姓名1","姓名2","姓名3","姓名4","姓名5","姓名6"));
				this.put("gender",Gender.FEMALE );
				this.put("email", "abc@abc.com");
				this.put("phone_area", "0571");
				this.put("phone_number", "85022088");
				this.put("company", DataGenerator.repeat("公司1","公司2","公司3","公司4","公司5","公司6"));
				this.put("role", UserRole.INNER_USER);
				this.put("user_level", UserLevel.GOLDEN);
				this.put("status", UserStatus.NEW);
				this.put("is_deleted", IS.N);
			}
		}).commit();
		db.table("qth_product_buy").clean().insert(3, new DataMap() {
			{
				this.put("id",DataGenerator.repeat(1,2,3));
				this.put("name", DataGenerator.repeat("测试产品1","测试产品2","测试产品3"));
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
				this.put("name", DataGenerator.repeat("测试类别1","测试类别2","测试类别3"));
				this.put("description", "描述信息xxx");
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
		product.setName("单元测试产品名称");
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
		//普通查询，类别为1的
		List<BuyProduct> list=buyProductDao.query(buyProductQuery);
		want.number(list.size()).eq(1);

	}

	@Test
	public void testQueryProduct() {
		//查询收件人为s1的消息
		BuyProductQuery buyProductQuery=new BuyProductQuery();
		List<Long> categoryIds=new ArrayList<Long>();
		categoryIds.add(1L);
		buyProductQuery.setCategoryIds(categoryIds);
		//普通查询，类别为1的
		List<BuyProduct> list=buyProductDao.query(buyProductQuery);
		want.number(list.size()).eq(3);
		//分页查询
		list=buyProductDao.query(buyProductQuery,0,2);
		want.number(list.size()).eq(2);
		//用name查询
		buyProductQuery.setName("产品1");
		list=buyProductDao.query(buyProductQuery);
		want.number(list.size()).eq(1);
		//检查附件查询时关联情况
		BuyProduct pro=list.get(0);
		want.number(pro.getImgs().size()).eq(3);
	}

	@Test
	public void testUpdateProduct() {
		BuyProductQuery buyProductQuery=new BuyProductQuery();
		buyProductQuery.setName("产品1");
		List<BuyProduct> list=buyProductDao.query(buyProductQuery);
		want.number(list.size()).eq(1);
		//主要测试换图片
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
		//附件还是3个
		want.number(ubp.getImgs().size()).eq(3);
		boolean res=false;
		//新的一定加进去了
		for(Attachment att:ubp.getImgs()){
			if(att.getPath().equalsIgnoreCase("/test/img4.jpb")){
				res=true;
				break;
			}
		}
		want.bool(res).eq(true);
		//老的一定删除了
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
