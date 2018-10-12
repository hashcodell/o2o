package com.ebaotech.o2o.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebaotech.o2o.dao.AreaDao;
import com.ebaotech.o2o.entity.Area;
import com.ebaotech.o2o.service.BaseServiceTest;

/**
 * @author Administrator
 * 2018年8月30日
 */
public class AreaDaoTest extends BaseServiceTest{
	
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea(){
		List<Area> areaList = areaDao.queryArea();
		Assert.assertEquals(2, areaList.size());
	}
	
	
}
