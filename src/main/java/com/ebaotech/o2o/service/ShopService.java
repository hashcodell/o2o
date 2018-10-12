package com.ebaotech.o2o.service;

import com.ebaotech.o2o.dto.ImageHolder;
import com.ebaotech.o2o.dto.ShopExecution;
import com.ebaotech.o2o.entity.Shop;
import com.ebaotech.o2o.exceptions.ShopOperationException;

/**
 * @author Administrator
 * 2018年9月10日
 */
public interface ShopService {
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
//	ShopExecution updateShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
