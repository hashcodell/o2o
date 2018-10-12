package com.ebaotech.o2o.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebaotech.o2o.dto.ImageHolder;
import com.ebaotech.o2o.dto.ShopExecution;
import com.ebaotech.o2o.entity.Area;
import com.ebaotech.o2o.entity.PersonInfo;
import com.ebaotech.o2o.entity.Shop;
import com.ebaotech.o2o.entity.ShopCategory;
import com.ebaotech.o2o.enums.ShopStateEnum;
import com.ebaotech.o2o.exceptions.ShopOperationException;

/**
 * @author Administrator
 * 2018年9月10日
 */
public class ShopServiceTest extends BaseServiceTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() throws ShopOperationException, FileNotFoundException{
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
//		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(10L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺3");
		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("d://a.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
		ShopExecution se = shopService.addShop(shop, imageHolder );
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	
//	@Test
//	public void testUpdateShop() throws ShopOperationException, FileNotFoundException{
//		Shop shop = new Shop();
//		PersonInfo owner = new PersonInfo();
//		Area area = new Area();
//		ShopCategory shopCategory = new ShopCategory();
//		owner.setUserId(1L);
//		area.setAreaId(2);
//		shopCategory.setShopCategoryId(11L);
//		shop.setOwner(owner);
//		shop.setArea(area);
//		shop.setShopCategory(shopCategory);
//		shop.setShopName("测试的店铺3");
//		shop.setShopDesc("test3");
//		shop.setShopAddr("test3");
//		shop.setPhone("test3");
//		shop.setCreateTime(new Date());
//		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
//		shop.setAdvice("审核中");
//		File shopImg = new File("d://a.jpg");
//		InputStream is = new FileInputStream(shopImg);
//		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
//		ShopExecution se = shopService.addShop(shop, imageHolder );
//		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
//	}
}
