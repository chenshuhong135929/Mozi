package com.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.PositionigMapper;
import com.sy.pojo.Positionig;
import com.sy.service.Positionigservice;
@Service
public class Positionigserviceimpl implements Positionigservice{
	@Autowired
	private PositionigMapper positionigMapper;

	@Override
	public boolean addPosition(Positionig p) {
		if (positionigMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			positionigMapper = (PositionigMapper) webApplicationContext
					.getBean("positionigMapper");
		}
		Integer num = positionigMapper.insertSelective(p);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	
	}
	
	
	
	
}
