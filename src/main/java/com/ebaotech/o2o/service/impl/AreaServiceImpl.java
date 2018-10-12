package com.ebaotech.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebaotech.o2o.dao.AreaDao;
import com.ebaotech.o2o.entity.Area;
import com.ebaotech.o2o.service.AreaService;

/**
 * @author Administrator
 * 2018年8月30日
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	AreaDao areaDao;
	/* (non-Javadoc)
	 * @see com.ebaotech.o2o.service.AreaService#getAreaList()
	 */
	@Override
	public List<Area> getAreaList() {
		// TODO Auto-generated method stub
		return areaDao.queryArea();
	}

}
