package com.ebaotech.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ebaotech.o2o.dto.ImageHolder;
import com.ebaotech.o2o.dto.ShopExecution;
import com.ebaotech.o2o.entity.Area;
import com.ebaotech.o2o.entity.PersonInfo;
import com.ebaotech.o2o.entity.Shop;
import com.ebaotech.o2o.entity.ShopCategory;
import com.ebaotech.o2o.enums.ShopStateEnum;
import com.ebaotech.o2o.exceptions.ShopOperationException;
import com.ebaotech.o2o.service.AreaService;
import com.ebaotech.o2o.service.ShopCategoryService;
import com.ebaotech.o2o.service.ShopService;
import com.ebaotech.o2o.util.CodeUtil;
import com.ebaotech.o2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Administrator
 * 2018年9月18日
 */

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		
		if(!CodeUtil.getVerifyCode(request)){
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传照片不能为空");
		}
		
		if(shop != null && shopImg != null) {
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution se;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
				se = shopService.addShop(shop, imageHolder);
				if(se.getState() == ShopStateEnum.CHECK.getState()){
					modelMap.put("success", true);
					
					@SuppressWarnings("unchecked")
					List<Shop> shopList = (List<Shop>)request.getSession().getAttribute("shopList");
					if(shopList == null || shopList.size() == 0){
						shopList = new ArrayList<>();
					}
					shopList.add(se.getShop());
					request.getSession().setAttribute("shopList", shopList);
				}else{
					modelMap.put("success" ,"false");
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success" ,"false");
				modelMap.put("errMsg", e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				modelMap.put("success" ,"false");
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
	}
	
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopInitInfo(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			shopCategoryList = shopCategoryService.queryShopCategory(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return  modelMap;
	}
}
