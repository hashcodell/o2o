package com.ebaotech.o2o.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebaotech.o2o.entity.ShopCategory;

/**
 * @author Administrator
 * 2018年9月18日
 */
public class ShopCategoryDaoTest extends BaseDaoTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testShopCategoryDaoTest(){
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		shopCategoryList = shopCategoryDao.queryCategory(new ShopCategory());
		Assert.assertEquals(15, shopCategoryList.size());
	}
}
