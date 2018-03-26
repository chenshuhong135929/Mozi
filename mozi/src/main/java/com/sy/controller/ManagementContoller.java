package com.sy.controller;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Advertising;
import com.sy.pojo.Management;
import com.sy.service.Managementservice;
import com.sy.utils.DeleteFileUtil;
import com.sy.utils.MD5Util;
import com.sy.utils.PageModel;

@Controller
@RequestMapping(value = "management", method = RequestMethod.POST)
public class ManagementContoller {

	@Autowired
	private Managementservice managementservice;

	@RequestMapping("addManagement")
	@ResponseBody
	public ResultBase addManagement(String account, String password,
			String name, Integer age, String gender, String position,
			String phone, String address,  String wechat,
			String qq,@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar) {
		new File("E:\\Project\\avatars").mkdirs();
		com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
		String st = managementservice.uploadavatar("avatars/" + avatar.getOriginalFilename());
		Management m =new Management();
		m.setAvatar(st);
		m.setAccount(account);
		password =MD5Util.MD5(password);
		m.setPassword(password);
		m.setGender(gender);
		m.setPosition(position);
		m.setPhone(phone);
		m.setWechat(wechat);
		m.setQq(qq);
		m.setAddress(address);
		m.setCreatetime(new Date());
		m.setAtlasttime(new Date());
		m.setAge(age);
		m.setName(name);
		ResultBase re = new ResultBase();
		if (managementservice.ifmanagement(account)) {
			boolean status = managementservice.addManagement(m);
			if (status) {
				re.setCode(200);
				re.setMessage("添加管理人员成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("添加管理人员失败！！！");
			}
		} else {
			re.setCode(305);
			re.setMessage("该管理人员存在！！！");
		}

		return re;
	}
	@RequestMapping("updateManagement")
	@ResponseBody
	public ResultBase updateManagement(String account, String password,Integer id,
			String name, Integer age, String gender, String position,
			String phone, String address,  String wechat,
			String qq,@RequestParam(value = "avatar", required = false) CommonsMultipartFile avatar) {
		String stimg = null ;
		if(avatar !=null){
			Management a =	managementservice.getmanagementid(id);
			if(a !=null){
				if(a.getAvatar() !=null && a.getAvatar() !="" && !a.getAvatar().equals("")){
					String[] st = a.getAvatar().split("/");
					DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
				}
			}
			new File("E:\\Project\\avatars").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\avatars", avatar);
			stimg = managementservice.uploadavatar("avatars/" + avatar.getOriginalFilename());	
	}
		
		Management m =new Management();
		m.setAvatar(stimg);
		m.setAccount(account);
		m.setGender(gender);
		m.setPosition(position);
		m.setPhone(phone);
		m.setWechat(wechat);
		m.setQq(qq);
		m.setAddress(address);
		m.setAge(age);
		m.setId(id);
		m.setName(name);
		ResultBase re = new ResultBase();
			boolean status = managementservice.updateManagement(m);
			if (status) {
				re.setCode(200);
				re.setMessage("修改管理人员成功！！！");
			} else {
				re.setCode(400);
				re.setMessage("修改管理人员失败！！！");
			}
		

		return re;
	}

	@RequestMapping("deleteManagement")
	@ResponseBody
	public ResultBase deleteManagement(Integer id) {
		ResultBase re = new ResultBase();
		boolean status = managementservice.deleteManagement(id);
		if (status) {
			re.setCode(200);
			re.setMessage("删除管理人员成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("删除管理人员失败！！！");
		}

		return re;

	}

	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<Management> pagemodel = managementservice.getusersone(pageNo,
				keyword);
		mo.setViewName("Management");
		mo.addObject("pagemodel", pagemodel);
		return mo;
	}
	@RequestMapping("getmanagementid")
	@ResponseBody
	public ResultData<Management> getmanagementid(Integer id){
		ResultData<Management> re = new ResultData<Management>();
		re.setCode(200);
		re.setData(managementservice.getmanagementid(id));
		return re;
	}
}
