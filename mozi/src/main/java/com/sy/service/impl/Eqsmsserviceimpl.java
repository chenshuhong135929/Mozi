package com.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.EqsmsMapper;
import com.sy.pojo.Eqsms;
import com.sy.service.Eqsmsservice;
@Service
public class Eqsmsserviceimpl implements Eqsmsservice {
	@Autowired
	private EqsmsMapper eqsmsMapper;

	@Override
	public boolean addEqsms(Eqsms e) {
		if (eqsmsMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			eqsmsMapper = (EqsmsMapper) webApplicationContext
					.getBean("eqsmsMapper");
		}
	 Integer num = eqsmsMapper.insertSelective(e);
	 if(num != 0){
		 return true;
	 }else {
		 return false;
	}
		
	}
	
	
}
