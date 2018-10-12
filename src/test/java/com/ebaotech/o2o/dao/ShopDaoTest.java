package com.ebaotech.o2o.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebaotech.o2o.entity.Area;
import com.ebaotech.o2o.entity.PersonInfo;
import com.ebaotech.o2o.entity.Shop;
import com.ebaotech.o2o.entity.ShopCategory;
import com.ebaotech.o2o.service.BaseServiceTest;

/**
 * @author Administrator
 * 2018年9月7日
 */
public class ShopDaoTest extends BaseServiceTest{
	
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void insertShopTest() {
		Area area = new Area();
		area.setAreaId(2);
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(10L);
		Shop shop = new Shop();
		shop.setShopName("testName");
		shop.setShopDesc("testDesc");
		shop.setShopAddr("testAddr");
		shop.setPhone("testPhone");
		shop.setShopImg("testShopImg");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("testAdvice");
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		
		Assert.assertEquals(1, shopDao.insertShop(shop));
	}
	
	@Test
	public void updateShopTest(){
		Shop shop = new Shop();
		shop.setShopId(36L);
		shop.setShopName("test2Name");
		shop.setShopDesc("test2Desc");
		shop.setShopAddr("test2Add");
		shop.setPriority(2);
		shop.setLastEditTime(new Date());
		
		int i = shopDao.updateShop(shop);
		Assert.assertEquals(1, i);
	}
}
