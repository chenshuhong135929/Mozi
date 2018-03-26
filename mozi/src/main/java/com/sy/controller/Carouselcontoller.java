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
import com.sy.pojo.Carousel;
import com.sy.pojo.Management;
import com.sy.service.Carouselservice;
import com.sy.service.Managementservice;
import com.sy.utils.DeleteFileUtil;
import com.sy.utils.PageModel;

@Controller
@RequestMapping(value = "carousel", method = RequestMethod.POST)
public class Carouselcontoller {
	@Autowired
	private Managementservice managementservice;
	@Autowired
	private Carouselservice carouselservice;
	@RequestMapping("addCarousel")
	@ResponseBody
	public ResultBase addCarousel(  String content,String title,@RequestParam(value = "img", required = false) CommonsMultipartFile img,HttpSession session) {
		Management m = (Management) session.getAttribute(Managementconstant.user);
		ResultBase re = new ResultBase();
		if(m !=null){
			new File("E:\\Project\\Carousel").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\Carousel", img);
			String st = managementservice.uploadavatar("Carousel/" + img.getOriginalFilename());
		
			Carousel a = new Carousel();
			a.setImg(st);
			a.setCreatetime(new Date());
			a.setManagementId(m.getId());
			a.setTitle(title);
				boolean status = carouselservice.addCarousel(a);
				if (status) {
					re.setCode(200);
					re.setMessage("添加图片成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("添加图片失败！！！");
				}
		}else {
			re.setCode(350);
			re.setMessage("权限补足！！！");
		}
		
		return re;
	}
	
	@RequestMapping("updateCarousel")
	@ResponseBody
	public ResultBase updateCarousel( Integer id, String content,String title,@RequestParam(value = "img", required = false) CommonsMultipartFile img,HttpSession session) {
		Management m = (Management) session.getAttribute(Managementconstant.user);
		ResultBase re = new ResultBase();
		if(m !=null){
			if(img !=null){
				Carousel a =	carouselservice.getCarousel(id);
				if(a !=null){
					if(a.getImg() !=null && a.getImg() !="" && !a.getImg().equals("")){
						String[] st = a.getImg().split("/");
						DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
					}
				}
				
		}
			
			new File("E:\\Project\\Carousel").mkdirs();
			com.sy.utils.StringUtil.arrayUploadFile("E:\\Project\\Carousel", img);
			String st = managementservice.uploadavatar("Carousel/" + img.getOriginalFilename());
		
			Carousel a = new Carousel();
			a.setCreatetime(new Date());
			a.setImg(st);
			a.setManagementId(m.getId());
			a.setId(id);
			a.setTitle(title);
				boolean status = carouselservice.updateCarousel(a);
				if (status) {
					re.setCode(200);
					re.setMessage("修改图片成功！！！");
				} else {
					re.setCode(400);
					re.setMessage("修改图片失败！！！");
				}
		}else {
			re.setCode(350);
			re.setMessage("权限补足！！！");
		}
		
		return re;
	}
	
	@RequestMapping("deleteCarousel")
	@ResponseBody
	public ResultBase deleteCarousel(Integer id) {
		ResultBase re = new ResultBase();
		Carousel a =	carouselservice.getCarousel(id);
		if(a !=null){
			if(a.getImg() !=null && a.getImg() !="" && !a.getImg().equals("")){
				String[] st = a.getImg().split("/");
				DeleteFileUtil.deleteFile("E:/Project/" + st[3] + "/" + st[4]);
			}
		}
		boolean status = carouselservice.deleteCarousel(id);
		if (status) {
			re.setCode(200);
			re.setMessage("删除图片成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("删除图片失败！！！");
		}

		return re;

	}

	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyword) {
		ModelAndView mo = new ModelAndView();
		PageModel<Carousel> pagemodel = carouselservice.getusersone(pageNo,
				keyword);
		mo.setViewName("carousel");
		mo.addObject("pageModel", pagemodel);
		return mo;
	}
	
	
	
}
