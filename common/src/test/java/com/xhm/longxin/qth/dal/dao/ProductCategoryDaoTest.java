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
				this.put("name", DataGenerator.repeat("测试类别1","测试类别2","测试类别3"));
				this.put("description", "描述信息xxx");
				this.put("is_material", DataGenerator.repeat("Y","Y","N"));
				this.put("is_deleted", "N");
			}
		}).commit();
	}
	@Test
	public void testAddProductCategory() {
		ProductCategory cate = new ProductCategory();
		cate.setName("测试类目123");
		cate.setIsMaterial(IS.Y);
		//保存
		want.bool(productCategoryDao.addProductCategory(cate)).eq(true);
		CategoryQuery categoryQuery=new CategoryQuery();
		//查询数量
		categoryQuery.setName("类目");
		want.number(productCategoryDao.queryCount(categoryQuery)).eq(1);
		categoryQuery.setName("类别");
		want.number(productCategoryDao.queryCount(categoryQuery)).eq(3);
		//查询结果
		categoryQuery.setName("类");
		want.number(productCategoryDao.queryCount(categoryQuery)).eq(4);
		List<ProductCategory> cateList=productCategoryDao.query(categoryQuery);
		want.object(cateList).notNull();
		want.number(cateList.size()).eq(4);
		//分页查询结果
		cateList=productCategoryDao.query(categoryQuery,0,2);
		want.object(cateList).notNull();
		want.number(cateList.size()).eq(2);

		categoryQuery.setName("1");
		want.number(productCategoryDao.queryCount(categoryQuery)).eq(2);
	}
}