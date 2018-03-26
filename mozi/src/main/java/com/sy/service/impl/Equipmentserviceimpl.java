package com.sy.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.EquipmentMapper;
import com.sy.pojo.Equipment;
import com.sy.service.Equipmentservice;
@Service
public class Equipmentserviceimpl implements Equipmentservice {
	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public boolean updateEquipment(Equipment e) {
		e.setUpdatetime(new Date());
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext
					.getBean("equipmentMapper");
		}
		Integer num = equipmentMapper.updateByPrimaryKeySelective(e);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Equipment selectequipment(Integer id) {
		// TODO Auto-generated method stub
		return equipmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Equipment selectquipmentimei(String imei) {
		if (equipmentMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			equipmentMapper = (EquipmentMapper) webApplicationContext
					.getBean("equipmentMapper");
		}
		return equipmentMapper.getequipment(imei);
	}

}
