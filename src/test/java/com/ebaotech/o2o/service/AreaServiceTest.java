package com.ebaotech.o2o.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebaotech.o2o.entity.Area;
import com.ebaotech.o2o.service.impl.AreaServiceImpl;

/**
 * @author Administrator
 * 2018年8月30日
 */
public class AreaServiceTest extends BaseServiceTest{
	
	@Autowired
	private AreaService areaService;
	
	@Test
	public void testAreaServiceImplTest(){
		List<Area> areaList = areaService.getAreaList();
		Assert.assertEquals("西苑", areaList.get(0).getAreaName());
		for(Area area:areaList){
			System.out.println(area);
		}
	}
}
