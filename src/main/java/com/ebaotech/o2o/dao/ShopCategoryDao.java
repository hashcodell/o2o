package com.ebaotech.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ebaotech.o2o.entity.ShopCategory;

/**
 * @author Administrator
 * 2018年9月18日
 */
public interface ShopCategoryDao {
	List<ShopCategory> queryCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
