package com.ebaotech.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebaotech.o2o.dao.ShopCategoryDao;
import com.ebaotech.o2o.entity.ShopCategory;
import com.ebaotech.o2o.service.ShopCategoryService;

/**
 * @author Administrator
 * 2018年9月18日
 */

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Override
	public List<ShopCategory> queryShopCategory(ShopCategory shopCategoryCondition) {
		
		return shopCategoryDao.queryCategory(shopCategoryCondition);
	}
	
	
}
