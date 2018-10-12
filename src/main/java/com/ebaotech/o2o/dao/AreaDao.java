/**
	 * 列出区域列表
	 * 
     * @authorAdministrator
     * @date2018年8月28日
	 */
package com.ebaotech.o2o.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebaotech.o2o.entity.Area;

/**
 * @author Administrator
 */

public interface AreaDao {
	
	List<Area> queryArea();
}
