package com.ebaotech.o2o.dao;

import com.ebaotech.o2o.entity.Shop;

/**
 * @author Administrator
 * 2018年9月7日
 */
public interface ShopDao {
	//新增店铺
	int insertShop(Shop shop);
	//更新店铺
	int updateShop(Shop shop);
	
}
