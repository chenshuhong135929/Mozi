package com.sy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.EquipmentDataMapper;
import com.sy.mapper.EquipmentMapper;
import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentData;
import com.sy.service.EquipmentDataservice;
import com.sy.service.Equipmentservice;
import com.sy.service.UserEqservice;
import com.sy.utils.PageModel;
import com.sy.vo.EquipmentDataimei;

@Service
public class EquipmentDataserviceimpl implements EquipmentDataservice {
	@Autowired
	private Equipmentservice equipmentservice;
	@Autowired
	private UserEqservice usereqservice;
	@Autowired
	private EquipmentDataMapper dataMapper;
	@Autowired
	private EquipmentMapper equipmentmapper;

	@Override
	public Equipment equipmentstatus(String eqStatus, String eqtype, String imei) {
		if (equipmentmapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			equipmentmapper = (EquipmentMapper) webApplicationContext
					.getBean("equipmentMapper");
		}
		Equipment e = equipmentmapper.getequipment(imei);

		if (e == null) {
			e = new Equipment();
			e.setEqStatus(eqStatus);
			e.setEqtype(eqtype);
			e.setImei(imei);
			e.setCreatetime(new Date());
			equipmentmapper.insertSelective(e);

		} else {
			e.setEqStatus(eqStatus);
			e.setEqtype(eqtype);
			e.setUpdatetime(new Date());
			equipmentmapper.updateByPrimaryKeySelective(e);
		}
		return equipmentmapper.getequipment(imei);
	}

	@Override
	public Equipment getimeiid(String imei) {
		if (equipmentmapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			equipmentmapper = (EquipmentMapper) webApplicationContext
					.getBean("equipmentMapper");
		}
		return equipmentmapper.getequipment(imei);
	}

	@Override
	public boolean addEquipmentData(EquipmentData e) {
		e.setCreatetime(new Date());

		if (dataMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			dataMapper = (EquipmentDataMapper) webApplicationContext
					.getBean("equipmentDataMapper");
		}
		Integer num = dataMapper.insertSelective(e);
		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PageModel<EquipmentDataimei> getusersone(Integer pageNo, String keyWord) {
		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=dataMapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<EquipmentDataimei>Feedbacks = null;
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    List<EquipmentData>ldate=dataMapper.list(parameter);
		    Feedbacks = new ArrayList<EquipmentDataimei>();
		    for(EquipmentData ds:ldate){
		    	EquipmentDataimei eq = new EquipmentDataimei(); 
		    	Integer eqid =usereqservice.geteqid(ds.getUserId());
		    	Equipment e=equipmentservice.selectequipment(eqid);
		    	eq.setEquipmentData(ds);
		    	eq.setImei(e.getImei());
		    	Feedbacks.add(eq);
		    }
		    PageModel<EquipmentDataimei> pageModel = new PageModel<EquipmentDataimei>(pageNo, pageSize,count, Feedbacks,"equipmentData/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}

}
