package com.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.UserEqMapper;
import com.sy.mapper.UserMapper;
import com.sy.pojo.Equipment;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.service.UserEqservice;
@Service
public class UserEqserviceimpl implements UserEqservice{
    @Autowired
	private UserEqMapper eqMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;
    
    @Autowired 
    private UserMapper usermapper;

	@Override
	public User getuserimei0(Integer id) {
		if(eqMapper ==null ){
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            eqMapper=(UserEqMapper)webApplicationContext.getBean("userEqMapper");
		}
		if(usermapper ==null ){
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            usermapper=(UserMapper)webApplicationContext.getBean("userMapper");
		}
		UserEq  ue =eqMapper.getuserimei0(id);
		return usermapper.selectByPrimaryKey(ue.getUserId());
	}

	@Override
	public Integer getimei(String imei) {
		if(eqMapper ==null ){
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            eqMapper=(UserEqMapper)webApplicationContext.getBean("userEqMapper");
		}
		if(equipmentMapper ==null ){
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            equipmentMapper=(EquipmentMapper)webApplicationContext.getBean("equipmentMapper");
		}
		Equipment e =equipmentMapper.getequipment(imei);
		UserEq u =eqMapper.getuserimei2(e.getId());
		return u.getUserId();
	}

	@Override
	public Integer geteqid(Integer userid) {
		if(eqMapper ==null ){
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            eqMapper=(UserEqMapper)webApplicationContext.getBean("userEqMapper");
		}
		return eqMapper.geteqid(userid);
	}


    
    
}
