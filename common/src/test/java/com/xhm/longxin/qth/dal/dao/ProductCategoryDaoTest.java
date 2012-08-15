/**
 *
 */
package com.xhm.longxin.qth.dal.dao;

import java.util.List;

import junit.framework.Assert;

import org.jtester.annotations.SpringBeanByName;
import org.jtester.core.IJTester.DataGenerator;
import org.jtester.core.IJTester.DataMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.xhm.longxin.qth.dal.constant.IS;
import com.xhm.longxin.qth.dal.dataobject.ProductCategory;
import com.xhm.longxin.qth.dal.query.CategoryQuery;

/**
 * @author ren.zhangr
 *
 */
public class ProductCategoryDaoTest extends BaseDaoTest {
	@SpringBeanByName
	private ProductCategoryDao productCategoryDao;

	@BeforeMethod
	public void setUp(){
		db.table("qth_pro_category").clean().insert(3, new DataMap() {
			{
				this.put("id",DataGenerator.repeat(1,2,3));
				this.put("name", DataGenerator.repeat("�������1","�������2","�������3"));
				this.put("description", "������Ϣxxx");
				this.put("is_material", DataGenerator.repeat("Y","Y","N"));
				this.put("is_deleted", "N");
			}
		}).commit();
	}
	@Test
	public void testAddProductCategory() {
		ProductCategory cate = new ProductCategory();
		cate.setName("������Ŀ123");
		cate.setIsMaterial(IS.Y);
		//����
		want.bool(productCategoryDao.addProductCategory(cate)).eq(true);
		CategoryQuery categoryQuery=new CategoryQuery();
		//��ѯ����
		categoryQuery.setName("��Ŀ");
		want.number(productCategoryDao.queryCount(categoryQuery)).eq(1);
		categoryQuery.setName("���");
		want.number(productCategoryDao.queryCount(categoryQuery)).eq(3);
		//��ѯ���
		categoryQuery.setName("��");
		want.number(productCategoryDao.queryCount(categoryQuery)).eq(4);
		List<ProductCategory> cateList=productCategoryDao.query(categoryQuery);
		want.object(cateList).notNull();
		want.number(cateList.size()).eq(4);
		//��ҳ��ѯ���
		cateList=productCategoryDao.query(categoryQuery,0,2);
		want.object(cateList).notNull();
		want.number(cateList.size()).eq(2);

		categoryQuery.setName("1");
		want.number(productCategoryDao.queryCount(categoryQuery)).eq(2);
	}
}