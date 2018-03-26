package com.sy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sy.service.EquipmentDataservice;
import com.sy.utils.PageModel;
import com.sy.vo.EquipmentDataimei;
@Controller
@RequestMapping(value = "equipmentData")
public class EquipmentDatacontoller {
	@Autowired
	private EquipmentDataservice equipmentdateservice;
	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<EquipmentDataimei> pagemodel = equipmentdateservice.getusersone(pageNo,
				keyword);
		mo.setViewName("equipmentData");
		mo.addObject("pagemodel", pagemodel);
		return mo;
	}
}
