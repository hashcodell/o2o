package com.ebaotech.o2o.service;

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
public class ShopCategoryServiceTest extends BaseServiceTest {

	@Autowired
	private ShopCategoryService shopCategoryService;
	@Test
	public void queryShopCategory() {
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		shopCategoryList = shopCategoryService.queryShopCategory(new ShopCategory());
		Assert.assertEquals(15, shopCategoryList.size());
	}

}
