package com.ebaotech.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebaotech.o2o.dao.ShopDao;
import com.ebaotech.o2o.dto.ImageHolder;
import com.ebaotech.o2o.dto.ShopExecution;
import com.ebaotech.o2o.entity.Shop;
import com.ebaotech.o2o.enums.ShopStateEnum;
import com.ebaotech.o2o.exceptions.ShopOperationException;
import com.ebaotech.o2o.service.ShopService;
import com.ebaotech.o2o.util.ImageUtil;
import com.ebaotech.o2o.util.PathUtil;
import com.jhlabs.image.ImageUtils;

/**
 * @author Administrator
 * 2018年9月10日
 * 店铺业务层
 */
@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;
	
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException{
		if(shop == null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			if(effectedNum <= 0){
				throw new ShopOperationException("店铺创建失败");
			}else{
				if(thumbnail.getImage() != null){
					try{
						addShopImg(shop, thumbnail);
					}catch(Exception e){
						throw new ShopOperationException("addShopImg error:" + e.getMessage());
					}
					effectedNum = shopDao.updateShop(shop);
					if(effectedNum <= 0){
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	/**
	 * @param shop
	 * @param thumbnail
	 */
	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
	}
}
