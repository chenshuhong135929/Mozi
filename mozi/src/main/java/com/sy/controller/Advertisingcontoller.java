package com.sy.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultBase;
import com.sy.constant.Managementconstant;
import com.sy.pojo.Advertising;
import com.sy.pojo.Management;
import com.sy.service.Advertisingservice;
import com.sy.service.Managementservice;
import com.sy.utils.DeleteFileUtil;
import com.sy.utils.PageModel;

@Controller
@RequestMapping(value = "advertising", method = RequestMethod.POST)
public class Advertisingcontoller {
	@Autowired
	private Managementservice managementservice;
	@Autowired
	private Advertisingservice advertisingservice; 

	@RequestMapping("addAdvertising")
	@ResponseBody
	public ResultBase addAdvertising(  String content,String title,@RequestParam(value = "imgVideo", required = false) CommonsMultipartFile imgVideo,HttpSession session) {
		Management m = (Management) session.getAttribute(Managementconstant.user);
		ResultBase re = new ResultBase();
		if(m !=null){
			new File("E:\\Project\\advertising").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\advertising", imgVideo);
			String st = managementservice.uploadavatar("advertising/" + imgVideo.getOriginalFilename());
		
			Advertising a = new Advertising();
			a.setContent(content);
			a.setCreatetime(new Date());
			a.setImgVideo(st);
			a.setManagementId(m.getId());
			a.setTitle(title);
				boolean status = advertisingservice.addAdvertising(a);
				if (status) {
					re.setCode(200);
					re.setMessage("添加广告成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("添加广告失败！！！");
				}
		}else {
			re.setCode(350);
			re.setMessage("权限补足！！！");
		}
		
		return re;
	}
	
	@RequestMapping("updateAdvertising")
	@ResponseBody
	public ResultBase updateAdvertising( Integer id, String content,String title,@RequestParam(value = "imgVideo", required = false) CommonsMultipartFile imgVideo,HttpSession session) {
		Management m = (Management) session.getAttribute(Managementconstant.user);
		ResultBase re = new ResultBase();
		if(m !=null){
			if(imgVideo !=null){
				Advertising a =	advertisingservice.getadvertisingid(id);
				if(a !=null){
					if(a.getImgVideo() !=null && a.getImgVideo() !="" && !a.getImgVideo().equals("")){
						String[] st = a.getImgVideo().split("/");
						DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
					}
				}
				
		}
			
			new File("E:\\Project\\advertising").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\advertising", imgVideo);
			String st = managementservice.uploadavatar("advertising/" + imgVideo.getOriginalFilename());
		
			Advertising a = new Advertising();
			a.setContent(content);
			a.setCreatetime(new Date());
			a.setImgVideo(st);
			a.setManagementId(m.getId());
			a.setId(id);
			a.setTitle(title);
				boolean status = advertisingservice.updateAdvertising(a);
				if (status) {
					re.setCode(200);
					re.setMessage("修改广告成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("修改广告失败！！！");
				}
		}else {
			re.setCode(350);
			re.setMessage("权限补足！！！");
		}
		
		return re;
	}
	
	@RequestMapping("deleteAdvertising")
	@ResponseBody
	public ResultBase deleteAdvertising(Integer id) {
		ResultBase re = new ResultBase();
		Advertising a =	advertisingservice.getadvertisingid(id);
		if(a !=null){
			if(a.getImgVideo() !=null && a.getImgVideo() !="" && !a.getImgVideo().equals("")){
				String[] st = a.getImgVideo().split("/");
				DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
			}
		}
		boolean status = advertisingservice.deleteAdvertising(id);
		if (status) {
			re.setCode(200);
			re.setMessage("删除广告成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("删除广告失败！！！");
		}

		return re;

	}

	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<Advertising> pagemodel = advertisingservice.getusersone(pageNo,
				keyword);
		mo.setViewName("advertising");
		mo.addObject("pageModel", pagemodel);
		return mo;
	}
	
	
}
