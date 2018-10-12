package com.ebaotech.o2o.service;

import java.util.List;

import com.ebaotech.o2o.entity.ShopCategory;

/**
 * @author Administrator
 * 2018年9月18日
 */
public interface ShopCategoryService {
	List<ShopCategory> queryShopCategory(ShopCategory shopCategoryCondition);
}
